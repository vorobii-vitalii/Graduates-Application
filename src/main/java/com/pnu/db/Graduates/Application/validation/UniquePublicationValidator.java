package com.pnu.db.Graduates.Application.validation;

import com.pnu.db.Graduates.Application.dto.StudentDegreeFormDto;
import com.pnu.db.Graduates.Application.repository.PublicationRepository;
import com.pnu.db.Graduates.Application.repository.StudentDegreeRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import reactor.core.scheduler.Schedulers;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniquePublicationValidator implements ConstraintValidator<UniquePublicationConstraint, StudentDegreeFormDto> {
    private final StudentDegreeRepository studentDegreeRepository;

    @SneakyThrows
    @Override
    public boolean isValid(StudentDegreeFormDto studentDegreeFormDto, ConstraintValidatorContext constraintValidatorContext) {
        final Long publicationId = studentDegreeFormDto.getPublicationId();
        if (publicationId == null) {
            return false;
        }
        return studentDegreeRepository
                .existsByPublicationIdAndStudentDegreeIdNotMatch(publicationId, studentDegreeFormDto.getId())
                .map(res -> !res)
                .log()
                .subscribeOn(Schedulers.boundedElastic())
                .toFuture()
                .get();

    }
}
