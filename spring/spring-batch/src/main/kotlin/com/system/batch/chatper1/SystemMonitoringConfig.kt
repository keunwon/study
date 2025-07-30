package com.system.batch.chatper1

import com.system.batch.log
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class SystemMonitoringConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
) {
    @Bean
    fun systemMonitoringJob(
        monitoringStep: Step,
        jobExecutionListener: BigBrotherJobExecutionListener,
    ): Job {
        return JobBuilder("systemMonitoringJob", jobRepository)
            .listener(jobExecutionListener)
            .start(monitoringStep)
            .build()
    }

    @Bean
    fun monitoringStep(): Step = StepBuilder("monitoringStep", jobRepository)
        .tasklet(
            { contribution, chunkContext ->
                log.info("monitoringStep")
                RepeatStatus.FINISHED
            },
            transactionManager,
        )
        .build()
}
