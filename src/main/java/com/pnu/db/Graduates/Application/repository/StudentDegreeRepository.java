package com.pnu.db.Graduates.Application.repository;


import com.pnu.db.Graduates.Application.dto.StudentDegreeDto;
import com.pnu.db.Graduates.Application.dto.StudentDegreeFormDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentDegreeRepository {
    Flux<StudentDegreeDto> findAll();
    Mono<Long> add(StudentDegreeFormDto studentDegreeFormDtoDto);
    Mono<Long> update(StudentDegreeFormDto studentDegreeFormDtoDto);
    Mono<StudentDegreeFormDto> findById(Long id);
    Mono<Boolean> existsByPublicationIdAndStudentDegreeIdNotMatch(Long publicationId, Long studentDegreeId);
    Mono<Integer> deleteById(Long id);
}
