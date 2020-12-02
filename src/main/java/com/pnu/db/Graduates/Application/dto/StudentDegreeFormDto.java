package com.pnu.db.Graduates.Application.dto;

import com.pnu.db.Graduates.Application.validation.AdmissionPrecedesGraduationConstraint;
import com.pnu.db.Graduates.Application.validation.UniquePublicationConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AdmissionPrecedesGraduationConstraint
@UniquePublicationConstraint
public class StudentDegreeFormDto {

    private Long id;

    @NotNull
    private Long studentId;

    @NotNull
    private Long degreeId;

    @NotNull
    private Long publicationId;

    @Positive
    private Integer admissionYear;

    @Positive
    private Integer graduationYear;

    private Boolean withHonour;
}
