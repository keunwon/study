package com.system.batch.chatper1

import com.system.batch.log
import java.util.Random
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class AdvancedSystemInfiltrationConfig(
    private val infiltrationPlanListener: InfiltrationPlanListener,
) {
    @Bean
    fun systemInfiltrationJob(
        jobRepository: JobRepository,
        reconStep: Step,
        attackStep: Step,
    ): Job {
        return JobBuilder("systemInfiltrationJob", jobRepository)
            .listener(infiltrationPlanListener)
            .start(reconStep)
            .next(attackStep)
            .build()
    }

    @Bean
    @Suppress("UNCHECKED_CAST")
    fun reconStep(
        jobRepository: JobRepository,
        transactionManger: PlatformTransactionManager,
    ): Step {
        return StepBuilder("reconStep", jobRepository)
            .tasklet(
                { contribution, chunkContext ->
                    val infiltrationPlan =
                        contribution.stepExecution.jobExecution.executionContext["infiltrationPlan"] as Map<String, String>
                    log.info("침투 준비 단계: {}", infiltrationPlan["targetSystem"])
                    log.info("필요한 도구: {}", infiltrationPlan["requiredTools"])
                    RepeatStatus.FINISHED
                },
                transactionManger,
            )
            .build()
    }

    @Bean
    fun attackStep(
        jobRepository: JobRepository,
        attackStepTasklet: Tasklet,
        platformTransactionManager: PlatformTransactionManager,
    ): Step {
        return StepBuilder("attackStep", jobRepository)
            .tasklet(attackStepTasklet, platformTransactionManager)
            .build()
    }

    @Bean
    @StepScope
    fun attackStepTasklet(
        @Value("#{jobExecutionContext['infiltrationPlan']}") infiltrationPlan: Map<String, String>,
    ): Tasklet {
        return Tasklet { contribution, chunkContext ->
            log.info("시스템 공격 중: {}", infiltrationPlan["targetSystem"]);
            log.info("목표: {}", infiltrationPlan["objective"]);

            val rand = Random()
            val infiltrationSuccess = rand.nextBoolean()

            if (infiltrationSuccess) {
                log.info("침투 성공! 획득한 데이터: {}", infiltrationPlan["targetData"]);
                contribution.stepExecution.jobExecution.executionContext.put("infiltrationResult", "TERMINATED")
            } else {
                log.info("침투 실패. 시스템이 우리를 감지했다.")
                contribution.stepExecution.jobExecution.executionContext.put("infiltrationResult", "DETECTED")
            }

            RepeatStatus.FINISHED
        }
    }
}

@Component
class InfiltrationPlanListener : JobExecutionListener {
    override fun beforeJob(jobExecution: JobExecution) {
        val targets = listOf("판교 서버실", "안산 데이터센터")
        val objectives = listOf("kill -9 실행", "rm -rf 전개", "chmod 000 적용", "/dev/null로 리다이렉션")
        val targetData = listOf("코어 덤프 파일", "시스템 로그", "설정 파일", "백업 데이터")
        val requiredTools = listOf("USB 킬러", "널 바이트 인젝터", "커널 패닉 유발기", "메모리 시퍼너")
        val infiltrationPlan = mapOf(
            "targetSystem" to targets.random(),
            "objective" to objectives.random(),
            "targetData" to targetData.random(),
            "requiredTools" to requiredTools.random(),
        )

        jobExecution.executionContext.put("infiltrationPlan", infiltrationPlan)
    }

    @Suppress("UNCHECKED_CAST")
    override fun afterJob(jobExecution: JobExecution) {
        val infiltrationResult = jobExecution.executionContext["infiltrationResult"] as String
        val infiltrationPlan = jobExecution.executionContext["infiltrationPlan"] as Map<String, Map<String, String>>

        log.info("타겟 '{}' 침투 결과: {}", infiltrationPlan["targetSystem"], infiltrationResult);

        if (infiltrationResult == "TERMINATED") {
            log.info("시스템 제거 완료. 다음 타겟 검색 중...");
        } else {
            log.info("철수한다. 다음 기회를 노리자.");
        }
    }
}
