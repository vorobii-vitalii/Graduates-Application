package com.pnu.db.Graduates.Application.controller;

import com.pnu.db.Graduates.Application.dto.PublicationDto;
import com.pnu.db.Graduates.Application.dto.StudentDto;
import com.pnu.db.Graduates.Application.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Controller
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String getAll(Model model) {
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(studentService.getAll(), 1);
        model.addAttribute("students", reactiveDataDrivenMode);
        return "students";
    }

    @PostMapping
    public Mono<String> processForm (
            @Valid @ModelAttribute StudentDto studentDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Помилка вводу");
            model.addAttribute("studentDto", studentDto);
            return Mono.just("student_form");
        }
        return studentService.add(studentDto).thenReturn("redirect:/students");
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("studentDto", new StudentDto());
        return "student_form";
    }

    @GetMapping("/delete")
    public Mono<String> delete(@RequestParam("id") Long id, Model model) {
        return studentService
                .deleteById(id)
                .thenReturn("redirect:/students");
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("studentDto", studentService.getById(id));
        return "student_form";
    }

}
