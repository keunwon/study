package com.system.batch.chatper1

import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.batch.support.transaction.ResourcelessTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class ZombieBatchConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
) {
    @Bean
    fun zombieProcessCleanupTasklet(): Tasklet {
        return ZombieProcessCleanupTasklet()
    }

    @Bean
    fun zombieCleanupStep(): Step {
        return StepBuilder("zombieCleanupStep", jobRepository)
            .tasklet(zombieProcessCleanupTasklet(), ResourcelessTransactionManager())
            .build()
    }

    @Bean
    fun zombieCleanJop(): Job {
        return JobBuilder("zombieCleanupJob", jobRepository)
            .start(zombieCleanupStep())
            .build()
    }
}

class ZombieProcessCleanupTasklet : Tasklet {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val processesToKill = 10
    private var killedProcesses = 0

    override fun execute(
        contribution: StepContribution,
        chunkContext: ChunkContext,
    ): RepeatStatus? {
        ++killedProcesses
        logger.info("프로제스 강제 종료: {}, {}", killedProcesses, processesToKill)

        if (killedProcesses >= processesToKill) {
            return RepeatStatus.CONTINUABLE
        }
        return RepeatStatus.FINISHED
    }
}
