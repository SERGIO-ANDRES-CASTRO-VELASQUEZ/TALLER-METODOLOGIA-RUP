package com.gestor_de_proyectos.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EstudianteValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidarEstudiante {
    String message() default "El estudiante debe tener un ID válido o un nombre válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}