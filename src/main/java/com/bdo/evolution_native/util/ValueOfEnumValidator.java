package com.bdo.evolution_native.util;

import lombok.Generated;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Generated
public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {

    private List<String> acceptedValues;

    /**
     * This method is used to initialize the Enum Class.
     *
     * @param annotation
     */
    @Override
    public void initialize(final ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants()).map(Enum::name)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to validate values of the Enum Class.
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {

        final boolean flag;
        if (StringUtils.isBlank(value)) {
            flag = true;
        } else {
            flag = acceptedValues.contains(value.toString());
        }
        return flag;
    }
}
