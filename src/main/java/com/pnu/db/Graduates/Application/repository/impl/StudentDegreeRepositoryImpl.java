package com.pnu.db.Graduates.Application.repository.impl;

import com.pnu.db.Graduates.Application.dto.StudentDegreeDto;
import com.pnu.db.Graduates.Application.dto.StudentDegreeFormDto;
import com.pnu.db.Graduates.Application.repository.StudentDegreeRepository;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.AllArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Repository
@AllArgsConstructor
public class StudentDegreeRepositoryImpl implements StudentDegreeRepository {

    public static final BiFunction<Row, RowMetadata, StudentDegreeDto> DTO_DETAILS_MAPPING_FUNCTION =
        (row, rowMetaData) -> StudentDegreeDto.builder()
            .id(row.get("student_degree_id", Long.class))
            .studentName(row.get("student_name", String.class))
            .degreeName(row.get("degree_name", String.class))
            .publicationTitle(row.get("publication_title", String.class))
            .admissionYear(row.get("admission_year", Integer.class))
            .graduationYear(row.get("graduation_year", Integer.class))
            .build();

    public static final BiFunction<Row, RowMetadata, StudentDegreeFormDto> DTO_FORM_MAPPING_FUNCTION =
            (row, rowMetaData) -> StudentDegreeFormDto.builder()
                    .id(row.get("student_degree_id", Long.class))
                    .studentId(row.get("student_id", Long.class))
                    .degreeId(row.get("degree_id", Long.class))
                    .publicationId(row.get("publication_id", Long.class))
                    .admissionYear(row.get("admission_year", Integer.class))
                    .graduationYear(row.get("graduation_year", Integer.class))
                    .build();

    private final DatabaseClient databaseClient;

    @Override
    public Flux<StudentDegreeDto> findAll() {
        return databaseClient
                .sql("select \n" +
                        "    sd.student_degree_id as student_degree_id, \n" +
                        "    concat(s.first_name, ' ', s.last_name) as student_name, \n" +
                        "    d.name as degree_name, \n" +
                        "    p.title as publication_title, \n" +
                        "    sd.admission_year as admission_year, \n" +
                        "    sd.graduation_year as graduation_year\n" +
                        "from student_degree sd\n" +
                        "left join student s on s.student_id = sd.student_id\n" +
                        "left join degree d on d.degree_id = sd.degree_id\n" +
                        "left join publications p on p.publication_id = sd.publication_id")
                .map(DTO_DETAILS_MAPPING_FUNCTION)
                .all();
    }

    @Override
    public Mono<Long> add(StudentDegreeFormDto studentDegreeFormDtoDto) {
        return databaseClient
                .sql("insert into student_degree \n" +
                        "(student_id, degree_id, publication_id, admission_year, graduation_year)\n" +
                        "values \n" +
                        "(:student_id, :degree_id, :publication_id, :admission_year, :graduation_year)")
                .bind("student_id", studentDegreeFormDtoDto.getStudentId())
                .bind("degree_id", studentDegreeFormDtoDto.getDegreeId())
                .bind("publication_id", studentDegreeFormDtoDto.getPublicationId())
                .bind("admission_year", studentDegreeFormDtoDto.getAdmissionYear())
                .bind("graduation_year", studentDegreeFormDtoDto.getGraduationYear())
                .fetch()
                .first()
                .map(r -> (Long) r.get("id"));
    }

    @Override
    public Mono<Long> update(StudentDegreeFormDto studentDegreeFormDtoDto) {
        return databaseClient
                .sql(
                    "update student_degree set " +
                        "student_id = :student_id ," +
                        "degree_id = :degree_id ," +
                        "publication_id = :publication_id ," +
                        "admission_year = :admission_year ," +
                        "graduation_year = :graduation_year " +
                    "where student_degree_id = :student_degree_id"
                )
                .bind("student_degree_id", studentDegreeFormDtoDto.getId())
                .bind("student_id", studentDegreeFormDtoDto.getStudentId())
                .bind("degree_id", studentDegreeFormDtoDto.getDegreeId())
                .bind("publication_id", studentDegreeFormDtoDto.getPublicationId())
                .bind("admission_year", studentDegreeFormDtoDto.getAdmissionYear())
                .bind("graduation_year", studentDegreeFormDtoDto.getGraduationYear())
                .fetch()
                .first()
                .map(r -> (Long) r.get("student_degree_id"));
    }

    @Override
    public Mono<StudentDegreeFormDto> findById(Long id) {
        return databaseClient
                .sql("select student_degree_id, student_id, degree_id, publication_id, admission_year, graduation_year\n" +
                        "from student_degree where student_degree_id = :id")
                .bind("id", id)
                .map(DTO_FORM_MAPPING_FUNCTION)
                .first();
    }

    @Override
    public Mono<Boolean> existsByPublicationIdAndStudentDegreeIdNotMatch(Long publicationId, Long studentDegreeId) {
        if (studentDegreeId == null) {
            return databaseClient
                    .sql("select sd.student_degree_id as id from student_degree sd " +
                            "where sd.publication_id = :publication_id")
                    .bind("publication_id", publicationId)
                    .fetch()
                    .all()
                    .count()
                    .map(c -> c > 0);
        }
        return databaseClient
                .sql("select sd.student_degree_id as id from student_degree sd " +
                        "where sd.publication_id = :publication_id and sd.student_degree_id != :student_degree_id")
                .bind("publication_id", publicationId)
                .bind("student_degree_id", studentDegreeId)
                .fetch()
                .all()
                .count()
                .map(c -> c > 0);
    }

    @Override
    public Mono<Integer> deleteById(Long id) {
        return databaseClient
                .sql("delete from student_degree where student_degree_id=:id")
                .bind("id", id)
                .fetch()
                .rowsUpdated();
    }
}
