package com.spring.sre.chapter02.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor

@Configuration
class TaskExecutorConfig {

    @Bean
    fun taskExecutor(): TaskExecutor {
        return TaskExecutor { }
    }
}
