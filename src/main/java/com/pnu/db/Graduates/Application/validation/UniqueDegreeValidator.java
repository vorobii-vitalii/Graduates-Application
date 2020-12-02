package com.pnu.db.Graduates.Application.validation;

import com.pnu.db.Graduates.Application.repository.DegreeRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import reactor.core.scheduler.Schedulers;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueDegreeValidator implements ConstraintValidator<UniqueDegreeConstraint, String> {
    private final DegreeRepository degreeRepository;

    public void initialize(UniqueDegreeConstraint constraint) {
    }

    @SneakyThrows
    public boolean isValid(String obj, ConstraintValidatorContext context) {
        return degreeRepository
                .existsByName(obj)
                .map(res -> !res)
                .subscribeOn(Schedulers.boundedElastic())
                .toFuture()
                .get();
    }

}
