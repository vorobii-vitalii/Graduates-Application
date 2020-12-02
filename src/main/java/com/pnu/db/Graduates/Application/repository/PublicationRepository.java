package com.pnu.db.Graduates.Application.repository;

import com.pnu.db.Graduates.Application.model.Publication;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends ReactiveCrudRepository<Publication, Long> {
}
