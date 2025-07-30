package com.system.batch.chatper1

import com.system.batch.log
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
@ConditionalOnProperty(name = ["spring.batch.job.name"], havingValue = "processTerminatorJob")
class SystemTerminatorConfig {
    // ./gradlew bootRun --args="--spring.batch.job.name=processTerminatorJob terminatorId=KILL-9,java.lang.String targetCount=5,java.lang.Long"
    @Bean
    fun processTerminatorJob(
        jobRepository: JobRepository,
        terminationStep: Step,
        validator: SystemDestructionValidator,
    ): Job {
        return JobBuilder("processTerminatorJob", jobRepository)
            .start(terminationStep)
            .build()
    }

    @Bean
    fun terminationStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        terminatorTasklet: Tasklet,
    ): Step {
        return StepBuilder("terminationStep", jobRepository)
            .tasklet(terminatorTasklet, transactionManager)
            .build()
    }

    @Bean
    @StepScope
    fun terminatorTasklet(
        @Value("#{jobParameters['terminatorId']}") terminatorId: String,
        @Value("#{jobParameters['targetCount']}") targetCount: Int,
    ): Tasklet {
        return Tasklet { contribution, chunkContext ->
            log.info("시스템 종결자 정보:")
            log.info("ID: {}", terminatorId)
            log.info("제거 대상 수: {}", targetCount)
            log.info("⚡ SYSTEM TERMINATOR {} 작전을 개시합니다.", terminatorId)
            log.info("☠️ {}개의 프로세스를 종료합니다.", targetCount)

            for (i in 1..targetCount) {
                log.info("💀 프로세스 {} 종료 완료!", i)
            }

            log.info("🎯 임무 완료: 모든 대상 프로세스가 종료되었습니다.")
            RepeatStatus.FINISHED
        }
    }
}
