package com.pnu.db.Graduates.Application.service;

import com.pnu.db.Graduates.Application.dto.StudentDto;
import com.pnu.db.Graduates.Application.helpers.Mapper;
import com.pnu.db.Graduates.Application.model.Publication;
import com.pnu.db.Graduates.Application.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService extends Mapper<Student, StudentDto> {
    Flux<StudentDto> getAll();
    Mono<StudentDto> getById(Long id);
    Mono<Student> add(StudentDto publicationDto);
    Mono<Void> deleteById(Long id);
}
