package com.spring.sre

import io.micrometer.health.ServiceLevelObjective
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import java.time.Duration

@Configuration
@LoadBalancerClient(
    name = "discovery-load-balancer",
    configuration = [DiscoveryLoadBalancerConfiguration::class]
)
class WebClientConfig {

    @LoadBalanced
    @Bean
    fun webClientBuilder(): WebClient {
        return WebClient.builder()
            .build()
    }
}

@Configuration
class DiscoveryLoadBalancerConfiguration {

    @Bean
    fun discoveryClientServiceInstances(context: ConfigurableApplicationContext): ServiceInstanceListSupplier {
        return ServiceInstanceListSupplier.builder()
            .withDiscoveryClient()
            .withZonePreference()
            .withHealthChecks()
            .withCaching()
            .build(context)
    }
}

@Configuration
class UtilizationServiceLevelObjective {

    @Bean
    fun apiUtilization(): ServiceLevelObjective {
        return ServiceLevelObjective
            .build("api.utilization")
            .baseUnit("requests")
            .failedMessage("Rate limit to 10,0000 requests/second.")
            .count {
                it.name("http.server.requests")
                    .tag("uri", "/person")
                    .tag("outcome", "SUCCESS")
            }
            .isLessThan(Duration.ofSeconds(10L))
    }
}
