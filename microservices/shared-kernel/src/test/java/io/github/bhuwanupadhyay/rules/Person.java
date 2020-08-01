package io.github.bhuwanupadhyay.rules;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import static io.github.bhuwanupadhyay.rules.SyntaxRule.IsRequired;

class Person {

    @NotBlank(message = IsRequired, groups = {SyntaxRule.class})
    @Max(value = 50, message = IsRequired, groups = {SyntaxRule.class})
    private final String name;

    Person(String name) {
        this.name = name;
    }
}
