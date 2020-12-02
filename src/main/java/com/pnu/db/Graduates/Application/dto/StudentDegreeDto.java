package com.pnu.db.Graduates.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDegreeDto {

    private Long id;

    private String studentName;

    private String degreeName;

    private String publicationTitle;

    private Integer admissionYear;

    private Integer graduationYear;

    private Boolean withHonour;

}
