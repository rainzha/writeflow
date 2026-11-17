package org.rainzha.writeflow.assembly.archunit.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(HexagonalArchitectureProperties.class)
public class HexagonalArchitectureConfig {
}