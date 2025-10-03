package com.gestor_de_proyectos.validation;

import com.gestor_de_proyectos.dto.EstudianteDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EstudianteValidator implements ConstraintValidator<ValidarEstudiante, EstudianteDTO> {

    @Override
    public void initialize(ValidarEstudiante constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EstudianteDTO estudiante, ConstraintValidatorContext context) {
        if (estudiante == null) {
            return true; // @NotNull maneja esto
        }

        boolean tieneId = estudiante.getId() != null && estudiante.getId() > 0;
        boolean tieneNombre = estudiante.getNombres() != null && !estudiante.getNombres().isBlank();

        // Debe tener al menos uno: ID o nombre
        if (!tieneId && !tieneNombre) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "El estudiante debe tener un ID válido o un nombre válido"
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
}