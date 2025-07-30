package com.system.batch.chatper1

import com.system.batch.log
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.stereotype.Component

@Component
class BigBrotherStepExecutionListener : StepExecutionListener {
    override fun beforeStep(stepExecution: StepExecution) {
        log.info("Step 구역 감시 시작. 모든 행동이 기록된다.");
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus? {
        log.info("Step 감시 종료. 모든 행동이 기록되었다.");
        log.info("Big Brother의 감시망에서 벗어날 수 없을 것이다.");
        return ExitStatus.COMPLETED
    }
}
