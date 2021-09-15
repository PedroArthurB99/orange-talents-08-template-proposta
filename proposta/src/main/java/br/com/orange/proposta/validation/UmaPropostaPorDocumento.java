package br.com.orange.proposta.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UmaPropostaPorDocumentoValidator.class)
public @interface UmaPropostaPorDocumento {

    String message() default "Já existe uma proposta deste cliente!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
