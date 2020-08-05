package io.github.bhuwanupadhyay.onlineshop.cart;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "io.github.bhuwanupadhyay.onlineshop.cart")
class CartServiceArchTest {

    public static final String APPLICATION_SERVICES = "Application Services";
    public static final String DOMAIN_MODEL = "Domain Model";
    public static final String OUTBOUND_ADAPTERS = "Outbound Adapter(s)";
    public static final String INBOUND_ADAPTERS = "Inbound Adapter(s)";

    @ArchTest
    public static final ArchRule package_rule_for_bounded_context = Architectures
            .layeredArchitecture()
            .layer(APPLICATION_SERVICES).definedBy("..cart.application..")
            .layer(DOMAIN_MODEL).definedBy("..cart.domain..")
            .layer(OUTBOUND_ADAPTERS).definedBy("..cart.infrastructure..")
            .layer(INBOUND_ADAPTERS).definedBy("..cart.interfaces..")
            .whereLayer(APPLICATION_SERVICES).mayOnlyBeAccessedByLayers(INBOUND_ADAPTERS)
            .whereLayer(DOMAIN_MODEL).mayOnlyBeAccessedByLayers(APPLICATION_SERVICES, OUTBOUND_ADAPTERS)
            .whereLayer(OUTBOUND_ADAPTERS).mayNotBeAccessedByAnyLayer()
            .whereLayer(INBOUND_ADAPTERS).mayNotBeAccessedByAnyLayer();

}
