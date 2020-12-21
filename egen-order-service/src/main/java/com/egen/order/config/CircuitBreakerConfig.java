//package com.egen.order.config;
//
//import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
//import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.Duration;
//
//@Configuration
//public class CircuitBreakerConfig {
//
//
//    public final static String SHIIPING_CLIENT_CIRCUIT_BREAKER_REGISTRY = "SHIPPING_CLIENT_CIRCUIT_BREAKER";
//
//
//    @Bean
//    public CircuitBreakerConfigCustomizer shippingClientCircuitBreaker(){
//        return CircuitBreakerConfigCustomizer
//                .of(SHIIPING_CLIENT_CIRCUIT_BREAKER_REGISTRY, builder -> {
//                    builder.slidingWindowSize(5);
//                    builder.failureRateThreshold(3);
//                    builder.waitDurationInOpenState(Duration.ofSeconds(10));
//                    builder.slowCallDurationThreshold(Duration.ofSeconds(5));
//                    builder.slowCallRateThreshold(100);
//                    builder.permittedNumberOfCallsInHalfOpenState(2);
//                    builder.slidingWindowType(io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED);
//                });
//    }
//
//
//}
