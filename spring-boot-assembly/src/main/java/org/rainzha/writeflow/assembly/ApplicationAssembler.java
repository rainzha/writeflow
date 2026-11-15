package org.rainzha.writeflow.assembly;

import org.rainzha.writeflow.kernel.application.annotation.Adapter;
import org.rainzha.writeflow.kernel.application.annotation.ApplicationService;
import org.rainzha.writeflow.kernel.application.annotation.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Assembles the application by wiring the hexagonal-architecture beans.
 *
 * <p>Component scanning is restricted to the Architecture Kernel stereotypes so that only
 * application services, adapters and domain services are picked up as beans, keeping the inner layers free of framework
 * annotations.</p>
 *
 * @author Enrique Medina Montenegro
 */
@EnableTransactionManagement
@Configuration(proxyBeanMethods = false)
@ComponentScan(
        basePackages = "org.rainzha.writeflow",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = { Component.class, Service.class }
        ),
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = { ApplicationService.class, Adapter.class, DomainService.class }
        )
)
class ApplicationAssembler {
}