package com.system.batch.chatper1

import com.system.batch.log
import java.io.File
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

class DeleteOldFilesTasklet(
    private val path: String,
    private val daysOld: Int,
) : Tasklet {
    override fun execute(
        contribution: StepContribution,
        chunkContext: ChunkContext,
    ): RepeatStatus? {
        val dir = File(path)
        val cutoffTime: Long = System.currentTimeMillis() - (daysOld * 24 * 60 * 60 * 1000L)
        val files = dir.listFiles()

        files?.forEach {
            if (it.lastModified() < cutoffTime) {
                if (it.delete()) {
                    log.info("🔥 파일 삭제: {}", it.getName())
                } else {
                    log.info("⚠️  파일 삭제 실패: {}", it.getName())
                }
            }
        }

        return RepeatStatus.FINISHED
    }
}
