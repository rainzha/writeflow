package org.rainzha.writeflow.assembly.archunit.test;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.rainzha.writeflow.assembly.archunit.checker.ArchitectureKernelChecker;
import org.rainzha.writeflow.assembly.archunit.config.HexagonalArchitectureConfig;
import org.rainzha.writeflow.assembly.archunit.config.HexagonalArchitectureProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AnalyzeClasses(packages = "org.rainzha.writeflow.kernel")
@SpringBootTest(classes = {
        HexagonalArchitectureConfig.class,
        ArchitectureKernelArchitectureTest.TestConfig.class})
class ArchitectureKernelArchitectureTest {

    @Configuration
    static class TestConfig {
        @Bean
        public ArchitectureKernelChecker architectureKernelChecker(
                final HexagonalArchitectureProperties properties) {

            return new ArchitectureKernelChecker(properties);
        }
    }

    @Test
    @Order(1)
    void loadProperties() {
    }

    @ArchTest
    @Order(2)
    public static final ArchTests architectureKernelRules =
            ArchTests.in(ArchitectureKernelChecker.class);
}