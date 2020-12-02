package com.pnu.db.Graduates.Application.validation;

import com.pnu.db.Graduates.Application.dto.StudentDegreeFormDto;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class AdmissionPrecedesGraduationValidator implements ConstraintValidator<AdmissionPrecedesGraduationConstraint, StudentDegreeFormDto> {

    @Override
    public void initialize(AdmissionPrecedesGraduationConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(StudentDegreeFormDto studentDegreeFormDto, ConstraintValidatorContext constraintValidatorContext) {
        return studentDegreeFormDto.getAdmissionYear() < studentDegreeFormDto.getGraduationYear();
    }
}
