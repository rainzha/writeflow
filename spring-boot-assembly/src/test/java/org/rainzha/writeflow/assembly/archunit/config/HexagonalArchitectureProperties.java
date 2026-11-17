package org.rainzha.writeflow.assembly.archunit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "hexagonal.architecture")
public record HexagonalArchitectureProperties(
        ArchitectureKernelProperties architectureKernel,
        DomainProperties domain,
        OutputPortsProperties outputPorts,
        InputPortsProperties inputPorts,
        CommandProperties command,
        QueryProperties query,
        HandlerProperties handler,
        AdapterProperties adapters) {

    public record ArchitectureKernelProperties(
            List<String> fqdns,
            List<String> allowedLibraries) {
    }

    public record DomainProperties(
            List<String> fqdns,
            List<String> allowedLibraries) {
    }

    public record OutputPortsProperties(
            List<String> fqdns,
            List<String> allowedLibraries) {
    }

    public record InputPortsProperties(
            List<String> fqdns,
            List<String> allowedLibraries) {
    }

    public record CommandProperties(
            List<String> fqdns,
            List<String> allowedLibraries) {
    }

    public record QueryProperties(
            List<String> fqdns,
            List<String> allowedLibraries) {
    }

    public record HandlerProperties(
            List<String> fqdns,
            List<String> allowedLibraries) {
    }

    public record AdapterProperties(
            List<String> fqdns) {
    }
}