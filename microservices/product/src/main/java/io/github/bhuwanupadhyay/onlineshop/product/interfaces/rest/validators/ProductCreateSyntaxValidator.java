package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest.validators;

import io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest.ProductCreate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductCreateSyntaxValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductCreate.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors e) {
        ProductCreate command = (ProductCreate) o;
        if (StringUtils.isBlank(command.getName())) {
            e.reject("name", "name.empty");
        }
        if (StringUtils.isBlank(command.getDescription())) {
            e.reject("description", "description.empty");
        }
    }
}
