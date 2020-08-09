package io.github.bhuwanupadhyay.onlineshop.cart;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;

/*

                    ".application.commandservices.",
                    ".application.queryservices..",
                    "..application.outboundservices..",
                    "..application.outboundservices.acl..",

                    "..domain.commands..",
                    "..domain.events..",
                    "..domain.services..",
                    "..domain.model.aggregates..",
                    "..domain.model.entities..",
                    "..domain.model.valueobjects..",

                    "..infrastructure.brokers..",
                    "..infrastructure.config..",
                    "..infrastructure.repositories.jpa..",
                    "..infrastructure.services.http..",

                    "..interfaces.rest..",
                    "..interfaces.events.."
 */
@AnalyzeClasses(packages = CodeArchTest.PACKAGE)
class CodeArchTest {

    public static final String PACKAGE = "io.github.bhuwanupadhyay.onlineshop.cart";

    @ArchTest
    static final ArchRule no_classes = ArchRuleDefinition.classes()
            .should()
            .resideInAnyPackage(PACKAGE + "..", "..application..", "..domain..", "..infrastructure..", "..interfaces..");

    @ArchTest
    static final ArchRule onion_dependencies_are_respected = Architectures
            .onionArchitecture()
            .domainModels("..domain.model..")
            .domainServices("..domain.services..")
            .applicationServices("..application..")
//            .adapter("outbound", "..infrastructure..")
            .adapter("inbound", "..interfaces..");


}
