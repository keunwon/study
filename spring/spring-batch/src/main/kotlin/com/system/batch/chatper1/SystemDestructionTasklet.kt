package com.system.batch.chatper1

import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class SystemDestructionTasklet : Tasklet {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun execute(
        contribution: StepContribution,
        chunkContext: ChunkContext,
    ): RepeatStatus? {
        val jobParameters = chunkContext.stepContext.stepExecution.jobParameters
        val targetSystem = jobParameters.getString("system.target")
        val destructionLevel = jobParameters.getLong("system.destruction.level")

        log.info("타겟 시스템: {}", targetSystem);
        log.info("파괴 레벨: {}", destructionLevel);

        return RepeatStatus.FINISHED
    }
}
