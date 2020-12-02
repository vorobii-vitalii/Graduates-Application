package com.pnu.db.Graduates.Application.service;

import com.pnu.db.Graduates.Application.dto.DegreeDto;
import com.pnu.db.Graduates.Application.helpers.Mapper;
import com.pnu.db.Graduates.Application.model.Degree;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DegreeService extends Mapper<Degree, DegreeDto> {
    Flux<DegreeDto> getAll();
    Mono<Degree> save(DegreeDto degreeDto);
    Mono<Void> deleteById(Integer id);
}
