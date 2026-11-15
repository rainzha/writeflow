package org.rainzha.writeflow.assembly;

import org.rainzha.command.spring.SpringCommandBus;
import org.rainzha.query.spring.SpringQueryBus;
import org.rainzha.writeflow.kernel.command.core.CommandBus;
import org.rainzha.writeflow.kernel.query.core.QueryBus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Assembles the application creating the necessary beans for the Command and Query Bus.
 *
 * @author Enrique Medina Montenegro
 */
@Configuration(proxyBeanMethods = false)
class CommandQueryBusAssembler {

    @Bean
    CommandBus commandBus(
            final ApplicationContext context) {
        return new SpringCommandBus(
                new org.rainzha.command.spring.Registry(context));
    }

    @Bean
    QueryBus queryBus(
            final ApplicationContext context) {
        return new SpringQueryBus(
                new org.rainzha.query.spring.Registry(context));
    }

}