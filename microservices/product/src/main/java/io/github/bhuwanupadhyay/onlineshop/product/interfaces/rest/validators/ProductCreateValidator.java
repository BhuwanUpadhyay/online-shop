package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest.validators;

import io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest.ProductCreate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;

public class ProductCreateValidator extends SyntaxValidator<ProductCreate> {

    @Override
    protected void syntaxRules(ProductCreate command, Errors e) {
        if (StringUtils.isBlank(command.getName())) {
            e.reject("name", "name.empty");
        }
        if (StringUtils.isBlank(command.getDescription())) {
            e.reject("description", "description.empty");
        }
    }
}
