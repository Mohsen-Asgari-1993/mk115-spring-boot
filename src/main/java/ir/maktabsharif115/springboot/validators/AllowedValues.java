package ir.maktabsharif115.springboot.validators;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {
        AllowedValues.AllowedValuesForString.class,
        AllowedValues.AllowedValuesForStringList.class
})
@Documented
public @interface AllowedValues {

    String message();

    String[] values();

    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class AllowedValuesForString
            implements ConstraintValidator<AllowedValues, String> {

        private Boolean required;
        private String message;
        private String[] values;

        @Override
        public void initialize(final AllowedValues constraintAnnotation) {
            this.required = constraintAnnotation.required();
            this.message = constraintAnnotation.message();
            this.values = constraintAnnotation.values();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            if (required) {
                return StringUtils.isNotBlank(value) && checkValues(value);
            } else {
                return StringUtils.isBlank(value) || checkValues(value);
            }
        }

        private boolean checkValues(String value) {
            return List.of(values).contains(value);
        }
    }

    class AllowedValuesForStringList implements
            ConstraintValidator<AllowedValues, Collection<String>> {

        private Boolean required;
        private String message;
        private String[] values;

        @Override
        public void initialize(final AllowedValues constraintAnnotation) {
            this.required = constraintAnnotation.required();
            this.message = constraintAnnotation.message();
            this.values = constraintAnnotation.values();
        }

        @Override
        public boolean isValid(Collection<String> value, ConstraintValidatorContext context) {
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            if (required) {
                return CollectionUtils.isNotEmpty(value) && checkValues(value);
            } else {
                return CollectionUtils.isEmpty(value) || checkValues(value);
            }
        }

        private boolean checkValues(Collection<String> value) {
            return new HashSet<>(List.of(values)).containsAll(value);
        }
    }

}