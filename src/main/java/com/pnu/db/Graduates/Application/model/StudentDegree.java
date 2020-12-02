package com.pnu.db.Graduates.Application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table( "student_degree")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDegree {

    @Id
    @Column("student_degree_id")
    private Long id;

    @Column("student_id")
    private Long studentId;

    @Column("degree_id")
    private Integer degreeId;

    @Column("publication_id")
    private Long publicationId;

    @Column("admission_year")
    private Integer admissionYear;

    @Column("graduation_year")
    private Integer graduationYear;

    @Column("with_honour")
    private Boolean withHonour;

}
