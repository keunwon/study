package com.system.batch.chapter1

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.converter.JsonJobParametersConverter
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
@ConditionalOnProperty(name = ["spring.batch.job.name"], havingValue = "terminatorJob")
class TerminatorConfig {
    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    fun jobParametersConverter(): JsonJobParametersConverter {
        return JsonJobParametersConverter()
    }

    // ./gradlew bootRun --args='--spring.batch.job.name=terminatorJob executionDate=2024-01-01,java.time.LocalDate startTime=2024-01-01T14:30:00,java.time.LocalDateTime'
    @Bean
    fun terminatorJob(
        jobRepository: JobRepository,
        terminationStep: Step,
    ): Job {
        return JobBuilder("terminatorJob", jobRepository)
            .start(terminationStep)
            .build()
    }

    @Bean
    fun terminationStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        terminatorTasklet3: Tasklet,
    ): Step {
        return StepBuilder("terminationStep", jobRepository)
            .tasklet(terminatorTasklet3, transactionManager)
            .build()
    }

    @Bean
    @StepScope
    fun terminatorTasklet(
        @Value("#{jobParameters['executionDate']}") executionDate: LocalDate,
        @Value("#{jobParameters['startTime']}") startTime: LocalDateTime,
    ): Tasklet {
        return Tasklet { contribution, chunkContext ->
            log.info("시스템 처형 정보:")
            log.info("처형 예정일: {}", executionDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")))
            log.info("작전 개시 시각: {}", startTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")))
            log.info("⚡ {}에 예정된 시스템 정리 작전을 개시합니다.", executionDate)
            log.info("💀 작전 시작 시각: {}", startTime)


            // 작전 진행 상황 추적
            var currentTime = startTime
            for (i in 1..3) {
                currentTime = currentTime.plusHours(1)
                log.info("☠️ 시스템 정리 {}시간 경과... 현재 시각:{}", i, currentTime.format(DateTimeFormatter.ofPattern("HH시 mm분")))
            }

            log.info("🎯 임무 완료: 모든 대상 시스템이 성공적으로 제거되었습니다.")
            log.info("⚡ 작전 종료 시각: {}", currentTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")))
            RepeatStatus.FINISHED
        }
    }

    //  ./gradlew bootRun --args='--spring.batch.job.name=terminatorJob questDifficulty=EASY,com.system.batch.configuration.TerminatorConfig$QuestDifficulty'
    @Bean
    @StepScope
    fun terminatorTasklet2(
        @Value("#{jobParameters['questDifficulty']}") questDifficulty: QuestDifficulty,
    ): Tasklet {
        return Tasklet { contribution: StepContribution?, chunkContext: ChunkContext? ->
            log.info("⚔️ 시스템 침투 작전 개시!")
            log.info("임무 난이도: {}", questDifficulty)

            val baseReward = 100
            val rewardMultiplier = when (questDifficulty) {
                QuestDifficulty.EASY -> 1
                QuestDifficulty.NORMAL -> 2
                QuestDifficulty.HARD -> 3
                QuestDifficulty.EXTREME -> 5
            }
            val totalReward = baseReward * rewardMultiplier
            log.info("💥 시스템 해킹 진행 중...")
            log.info("🏆 시스템 장악 완료!")
            log.info("💰 획득한 시스템 리소스: {} 메가바이트", totalReward)
            RepeatStatus.FINISHED
        }
    }

    @Bean
    @StepScope
    fun terminatorTasklet3(
        @Value("#{jobParameters['infiltrationTargets']}") infiltrationTargets: String,
    ): Tasklet {
        return Tasklet { contribution, chunkContext ->
            val targets = infiltrationTargets.split(",")

            log.info("⚡ 침투 작전 개시")
            log.info("첫 번째 타겟: {} 침투 시작", targets[0])
            log.info("마지막 타겟: {} 에서 집결", targets[1])
            log.info("🎯 임무 전달 완료")

            RepeatStatus.FINISHED
        }
    }

    enum class QuestDifficulty {
        EASY, NORMAL, HARD, EXTREME
    }
}
