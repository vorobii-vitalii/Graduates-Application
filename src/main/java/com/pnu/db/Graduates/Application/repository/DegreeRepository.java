package com.pnu.db.Graduates.Application.repository;

import com.pnu.db.Graduates.Application.model.Degree;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DegreeRepository extends ReactiveCrudRepository<Degree, Integer> {
    Mono<Boolean> existsByName(String name);
}
