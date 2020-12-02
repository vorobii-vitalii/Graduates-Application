package com.pnu.db.Graduates.Application.service.impl;

import com.pnu.db.Graduates.Application.dto.StudentDto;
import com.pnu.db.Graduates.Application.model.Publication;
import com.pnu.db.Graduates.Application.model.Student;
import com.pnu.db.Graduates.Application.repository.StudentRepository;
import com.pnu.db.Graduates.Application.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Flux<StudentDto> getAll() {
        return studentRepository
                .findAll()
                .map(this::mapEntityToDto);
    }

    @Override
    public Mono<StudentDto> getById(Long id) {
        return studentRepository
                .findById(id)
                .map(this::mapEntityToDto);
    }

    @Override
    public Mono<Student> add(StudentDto publicationDto) {
        final Student student = mapDtoToEntity(publicationDto);
        return studentRepository.save(student);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return studentRepository.deleteById(id);
    }

    @Override
    public StudentDto mapEntityToDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public Student mapDtoToEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

}
