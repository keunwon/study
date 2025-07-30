package com.system.batch.chatper1

import com.system.batch.log
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.stereotype.Component

@Component
class BigBrotherJobExecutionListener : JobExecutionListener {
    override fun beforeJob(jobExecution: JobExecution) {
        log.info("시스템 감시 시작. 모든 작업을 내 통제 하에 둔다.");
    }

    override fun afterJob(jobExecution: JobExecution) {
        log.info("작업 종료. 할당된 자원 정리 완료.");
        log.info("시스템 상태: {}", jobExecution.getStatus());
    }
}
