package com.system.batch.chatper1

import com.system.batch.log
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
@ConditionalOnProperty(name = ["spring.batch.job.name"], havingValue = "killDashNineJob")
class SystemDestructionConfig {
    @Bean
    fun killDashNineJob(
        jobRepository: JobRepository,
        terminationStep: Step,
    ): Job {
        return JobBuilder("killDashNineJob", jobRepository)
            .start(terminationStep)
            .build()
    }

    @Bean
    fun terminationStep(
        jobRepository: JobRepository,
        platformTransactionManager: PlatformTransactionManager,
    ): Step {
        return StepBuilder("terminationStep", jobRepository)
            .tasklet(
                { contribution, chunkContext ->
                    log.info("시스템 제거 프로토콜 실행 중...")
                    RepeatStatus.FINISHED
                },
                platformTransactionManager,
            )
            .build()
    }

    @Bean
    @JobScope
    fun systemTerminationListener(
        @Value("#{jobParameters['terminationType']}") terminationType: String,
    ): JobExecutionListener {
        return object : JobExecutionListener {
            override fun beforeJob(jobExecution: JobExecution) {
                log.info("시스템 제거 시작! 제거 방식: {}", terminationType);
                super.beforeJob(jobExecution)
            }

            override fun afterJob(jobExecution: JobExecution) {
                log.info("작전 종료! 시스템 상태: {}", jobExecution.status);
            }
        }
    }
}
