package com.pnu.db.Graduates.Application.service;

import com.pnu.db.Graduates.Application.dto.StudentDegreeDto;
import com.pnu.db.Graduates.Application.dto.StudentDegreeFormDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentDegreeService {
    Flux<StudentDegreeDto> getAll();
    Mono<StudentDegreeFormDto> getById(Long id);
    Mono<Long> add(StudentDegreeFormDto studentDegreeFormDtoDto);
    Mono<Long> update(StudentDegreeFormDto studentDegreeFormDtoDto);
    Mono<Void> deleteById(Long id);
}
