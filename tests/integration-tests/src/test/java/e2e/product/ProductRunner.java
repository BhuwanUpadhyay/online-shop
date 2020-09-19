package e2e.product;

import com.intuit.karate.junit5.Karate;

class ProductRunner {

    @Karate.Test
    Karate testProduct() {
        return Karate.run("product").relativeTo(getClass());
    }
}
