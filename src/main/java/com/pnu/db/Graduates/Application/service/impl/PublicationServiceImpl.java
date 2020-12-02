package com.pnu.db.Graduates.Application.service.impl;

import com.pnu.db.Graduates.Application.dto.PublicationDto;
import com.pnu.db.Graduates.Application.dto.StudentDegreeDto;
import com.pnu.db.Graduates.Application.model.Publication;
import com.pnu.db.Graduates.Application.repository.PublicationRepository;
import com.pnu.db.Graduates.Application.service.PublicationService;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
@AllArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    public static final BiFunction<Row, RowMetadata, PublicationDto> DTO_DETAILS_MAPPING_FUNCTION =
            (row, rowMetaData) -> PublicationDto.builder()
                    .id(row.get("publication_id", Long.class))
                    .title(row.get("title", String.class))
                    .url(row.get("url", String.class))
                    .build();

    private final PublicationRepository publicationRepository;
    private final ModelMapper modelMapper;
    private final DatabaseClient databaseClient;


    @Override
    public Flux<PublicationDto> getAll() {
        return publicationRepository
                .findAll()
                .map(this::mapEntityToDto);
    }

    @Override
    public Mono<PublicationDto> getById(Long id) {
        return publicationRepository
                .findById(id)
                .map(this::mapEntityToDto);
    }

    @Override
    public Flux<PublicationDto> getAllWherePublicationIsNotAttached() {
        return databaseClient
                .sql("select \n" +
                        "p.publication_id as publication_id, \n" +
                        "p.title as title, \n" +
                        "p.url as url \n" +
                        "from publications p \n" +
                        "left join student_degree sd\n" +
                        "on p.publication_id = sd.publication_id\n" +
                        "where sd.publication_id is null"
                )
                .map(DTO_DETAILS_MAPPING_FUNCTION)
                .all();
    }

    @Override
    public Flux<PublicationDto> getAllWherePublicationIsNotAttachedOrStudentDegreeIdMatch(Long studentDegreeId) {
        return databaseClient
                .sql(
                        "select \n" +
                            "p.publication_id as publication_id, \n" +
                            "p.title as title, \n" +
                            "p.url as url\n" +
                        "from publications p \n" +
                        "left join student_degree sd\n" +
                        "on p.publication_id = sd.publication_id\n" +
                        "where sd.publication_id is null\n" +
                        "\n" +
                        "union all\n" +
                        "\n" +
                        "select \n" +
                            "p.publication_id as publication_id, \n" +
                            "p.title as title, \n" +
                            "p.url as url\n" +
                        "from publications p\n" +
                        "inner join student_degree sd\n" +
                        "on p.publication_id = sd.publication_id\n" +
                        "where sd.student_degree_id = :id"
                )
                .bind("id", studentDegreeId)
                .map(DTO_DETAILS_MAPPING_FUNCTION)
                .all();
    }

    @Override
    public Mono<Publication> add(PublicationDto publicationDto) {
        final Publication publication = mapDtoToEntity(publicationDto);
        return publicationRepository.save(publication);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return publicationRepository.deleteById(id);
    }

    @Override
    public PublicationDto mapEntityToDto(Publication publication) {
        return modelMapper.map(publication, PublicationDto.class);
    }

    @Override
    public Publication mapDtoToEntity(PublicationDto publicationDto) {
        return modelMapper.map(publicationDto, Publication.class);
    }
}
