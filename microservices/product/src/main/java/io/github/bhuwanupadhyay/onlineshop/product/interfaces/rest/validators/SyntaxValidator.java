package io.github.bhuwanupadhyay.onlineshop.product.interfaces.rest.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.reflect.ParameterizedType;

public abstract class SyntaxValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return getBodyType().equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        T command = (T) o;
        this.syntaxRules(command, errors);
    }

    protected abstract void syntaxRules(T command, Errors e);

    @SuppressWarnings("unchecked")
    private Class<T> getBodyType() {
        try {
            String typeName = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
            return (Class<T>) Class.forName(typeName);
        } catch (Exception e) {
            throw new RuntimeException("Class is not parametrized with generic type!!! Please use extends <> ", e);
        }
    }
}
