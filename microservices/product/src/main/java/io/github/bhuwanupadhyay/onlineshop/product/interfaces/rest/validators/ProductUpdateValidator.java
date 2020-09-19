package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest.validators;

import io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest.ProductUpdate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;

public class ProductUpdateValidator extends SyntaxValidator<ProductUpdate> {

    @Override
    protected void syntaxRules(ProductUpdate command, Errors e) {
        if (StringUtils.isBlank(command.getName().orElse(null))) {
            e.reject("name", "name.empty");
        }
        if (StringUtils.isBlank(command.getDescription().orElse(null))) {
            e.reject("description", "description.empty");
        }
    }
}
