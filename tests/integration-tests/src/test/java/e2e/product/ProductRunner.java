package e2e.product;

import com.intuit.karate.junit5.Karate;

class ProductRunner {

    @Karate.Test
    Karate testInventories() {
        return Karate.run("product").relativeTo(getClass());
    }
}
