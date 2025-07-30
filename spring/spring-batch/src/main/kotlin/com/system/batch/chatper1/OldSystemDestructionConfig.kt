package com.system.batch.chatper1

import com.system.batch.log
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersInvalidException
import org.springframework.batch.core.JobParametersValidator
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.DefaultJobParametersValidator
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class OldSystemDestructionConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
) {
    @Bean
    fun systemDestructionJob(
        validator: SystemDestructionValidator,
        systemDestructionStep: Step,
    ): Job {
        return JobBuilder("systemDestructionJob", jobRepository)
            .validator(
                DefaultJobParametersValidator(
                    arrayOf("destructionPower"),
                    arrayOf("targetSystem"),
                )
            )
            .validator(validator)
            .start(systemDestructionStep)
            .build()
    }

    @Bean
    @JobScope
    fun systemDestructionStep(
        @Value("#{jobParameters['destructionPower']}") destructionPower: Long,
    ): Step {
        return StepBuilder("systemDestructionStep", jobRepository)
            .tasklet(
                { contribution, chunkContext ->
                    log.info("destructionPower: $destructionPower")
                    RepeatStatus.FINISHED
                },
                transactionManager,
            )
            .build()
    }
}

@Component
class SystemDestructionValidator : JobParametersValidator {
    override fun validate(parameters: JobParameters?) {
        parameters ?: throw JobParametersInvalidException("파라미터가 null 입니다")

        val destructionPower = parameters.getLong("destructionPower")
            ?: throw JobParametersInvalidException("destructionPower 파라미터는 필수값입니다")

        if (destructionPower > 9L) {
            throw JobParametersInvalidException("파괴력 수준이 허용치를 초과했습니다: $destructionPower (최대 허용치: 9)")
        }
    }
}
