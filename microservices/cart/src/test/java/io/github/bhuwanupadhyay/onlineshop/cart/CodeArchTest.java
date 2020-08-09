package io.github.bhuwanupadhyay.onlineshop.cart;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = CodeArchTest.PACKAGE)
class CodeArchTest {

    public static final String PACKAGE = "io.github.bhuwanupadhyay.onlineshop.cart";

    @ArchTest
    static final ArchRule classes_are_under_packages = ArchRuleDefinition.classes()
            .should()
            .resideInAnyPackage(PACKAGE, "..application..", "..domain..", "..infrastructure..", "..interfaces..");

    @ArchTest
    static final ArchRule onion_dependencies_are_respected = Architectures
            .onionArchitecture()
            .domainModels("..domain.model..")
            .domainServices("..domain.services..")
            .applicationServices("..application..")
            .adapter("outbound", "..infrastructure..")
            .adapter("inbound", "..interfaces..");


}
