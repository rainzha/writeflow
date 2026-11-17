package org.rainzha.writeflow.assembly.archunit.checker;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.rainzha.writeflow.assembly.archunit.config.HexagonalArchitectureProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@Component
public class ArchitectureKernelChecker {
    private static HexagonalArchitectureProperties properties;

    public ArchitectureKernelChecker(final HexagonalArchitectureProperties properties) {
        ArchitectureKernelChecker.properties = properties;
    }

    @ArchTest
    public static void checkRules(final JavaClasses javaClasses) {
        if (properties == null) {
            throw new IllegalStateException(
                    "HexagonalArchitectureProperties have not been initialized");
        }

        final List<String> architectureKernelPackage =
                properties.architectureKernel().fqdns();
        if (architectureKernelPackage == null || architectureKernelPackage.isEmpty()) {
            throw new IllegalStateException(
                    "Architecture kernel FQDN is not configured in the properties");
        }

        final List<String> allowedLibraries = getAllowedLibraries();
        final String[] allowedPackages =
                getAllowedPackages(allowedLibraries);

        final ArchRule architectureKernelDependencyRule = classes()
                .that().resideInAnyPackage(
                        architectureKernelPackage.toArray(new String[0]))
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage(allowedPackages);

        architectureKernelDependencyRule.check(javaClasses);
//        FreezingArchRule.freeze(architectureKernelDependencyRule)
//                .check(javaClasses);
    }

    static List<String> getAllowedLibraries() {
        return properties.architectureKernel().allowedLibraries();
    }

    static String[] getAllowedPackages(
            final List<String> allowedLibraries) {
        final List<String> allowedPackages =
                new ArrayList<>(allowedLibraries);
        allowedPackages.addAll(properties.architectureKernel().fqdns());
        return allowedPackages.toArray(new String[0]);
    }
}
