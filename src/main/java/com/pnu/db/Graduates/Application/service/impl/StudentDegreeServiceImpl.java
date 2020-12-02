package com.pnu.db.Graduates.Application.service.impl;

import com.pnu.db.Graduates.Application.dto.StudentDegreeDto;
import com.pnu.db.Graduates.Application.dto.StudentDegreeFormDto;
import com.pnu.db.Graduates.Application.repository.StudentDegreeRepository;
import com.pnu.db.Graduates.Application.service.StudentDegreeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StudentDegreeServiceImpl implements StudentDegreeService {
    private final StudentDegreeRepository studentDegreeRepository;

    @Override
    public Flux<StudentDegreeDto> getAll() {
        return studentDegreeRepository.findAll();
    }

    @Override
    public Mono<StudentDegreeFormDto> getById(Long id) {
        return studentDegreeRepository.findById(id);
    }

    @Override
    public Mono<Long> add(StudentDegreeFormDto studentDegreeFormDtoDto) {
        return studentDegreeRepository.add(studentDegreeFormDtoDto);
    }

    @Override
    public Mono<Long> update(StudentDegreeFormDto studentDegreeFormDtoDto) {
        return studentDegreeRepository.update(studentDegreeFormDtoDto);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return studentDegreeRepository
                .deleteById(id)
                .then();
    }

}
