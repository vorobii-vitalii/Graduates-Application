package com.pnu.db.Graduates.Application.service;

import com.pnu.db.Graduates.Application.dto.PublicationDto;
import com.pnu.db.Graduates.Application.helpers.Mapper;
import com.pnu.db.Graduates.Application.model.Publication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PublicationService extends Mapper<Publication, PublicationDto> {
    Flux<PublicationDto> getAll();
    Mono<PublicationDto> getById(Long id);
    Flux<PublicationDto> getAllWherePublicationIsNotAttached();
    Flux<PublicationDto> getAllWherePublicationIsNotAttachedOrStudentDegreeIdMatch(Long studentDegreeId);
    Mono<Publication> add(PublicationDto publicationDto);
    Mono<Void> deleteById(Long id);
}
