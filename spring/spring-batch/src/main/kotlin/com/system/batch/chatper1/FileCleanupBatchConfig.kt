package com.system.batch.chatper1

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class FileCleanupBatchConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
) {
    @Bean
    fun deleteOldFilesTasklet(): Tasklet {
        return DeleteOldFilesTasklet("/path/to/temp", 30)
    }

    @Bean
    fun deleteOldFilesStep(): Step {
        return StepBuilder("deleteOldFilesStep", jobRepository)
            .tasklet(deleteOldFilesTasklet(), transactionManager)
            .build()
    }

    @Bean
    fun deleteOldFilesJob(): Job {
        return JobBuilder("deleteOldFilesJob", jobRepository)
            .start(deleteOldFilesStep())
            .build()
    }
}
