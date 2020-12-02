package com.pnu.db.Graduates.Application.service.impl;

import com.pnu.db.Graduates.Application.dto.DegreeDto;
import com.pnu.db.Graduates.Application.model.Degree;
import com.pnu.db.Graduates.Application.repository.DegreeRepository;
import com.pnu.db.Graduates.Application.service.DegreeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DegreeServiceImpl implements DegreeService {
    private final DegreeRepository degreeRepository;
    private final ModelMapper modelMapper;

    @Override
    public Flux<DegreeDto> getAll() {
        return degreeRepository
                .findAll()
                .map(this::mapEntityToDto);
    }

    @Override
    public Mono<Degree> save(DegreeDto degreeDto) {
        final Degree degree = mapDtoToEntity(degreeDto);
        return degreeRepository.save(degree);
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return degreeRepository.deleteById(id);
    }

    @Override
    public DegreeDto mapEntityToDto(Degree degree) {
        return modelMapper.map(degree, DegreeDto.class);
    }

    @Override
    public Degree mapDtoToEntity(DegreeDto degreeDto) {
        return modelMapper.map(degreeDto, Degree.class);
    }
}
