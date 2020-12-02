package com.pnu.db.Graduates.Application.controller;

import com.pnu.db.Graduates.Application.dto.StudentDegreeFormDto;
import com.pnu.db.Graduates.Application.service.DegreeService;
import com.pnu.db.Graduates.Application.service.PublicationService;
import com.pnu.db.Graduates.Application.service.StudentDegreeService;
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
@RequestMapping("/student-degrees")
@AllArgsConstructor
public class StudentDegreeController {
    private final StudentDegreeService studentDegreeService;
    private final PublicationService publicationService;
    private final StudentService studentService;
    private final DegreeService degreeService;

    @GetMapping
    public String showAll(Model model) {
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(studentDegreeService.getAll(), 1);
        model.addAttribute("studentsDegrees", reactiveDataDrivenMode);
        return "student-degrees";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        IReactiveDataDriverContextVariable publicationsVariable =
                new ReactiveDataDriverContextVariable(publicationService.getAllWherePublicationIsNotAttached(), 1);

        model.addAttribute("degrees", degreeService.getAll());
        model.addAttribute("students", studentService.getAll());
        model.addAttribute("publications", publicationsVariable);
        model.addAttribute("studentDegreeDto", new StudentDegreeFormDto());

        return "students-degree-form";
    }

    @PostMapping("/new")
    public Mono<String> processNewStudentDegree(
            @Valid @ModelAttribute StudentDegreeFormDto studentDegreeFormDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            IReactiveDataDriverContextVariable publicationsVariable =
                    new ReactiveDataDriverContextVariable(publicationService.getAllWherePublicationIsNotAttached(), 1);
            model.addAttribute("degrees", degreeService.getAll());
            model.addAttribute("students", studentService.getAll());
            model.addAttribute("publications", publicationsVariable);
            model.addAttribute("studentDegreeDto", studentDegreeFormDto);

            model.addAttribute("error", "Помилка");
            return Mono.just("students-degree-form");
        }
        return studentDegreeService
                .add(studentDegreeFormDto)
                .thenReturn("redirect:/student-degrees");
    }

    @GetMapping("/delete")
    public Mono<String> delete(@RequestParam("id") Long id, Model model) {
        return studentDegreeService
                .deleteById(id)
                .thenReturn("redirect:/student-degrees");
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model) {
        IReactiveDataDriverContextVariable publicationsVariable =
            new ReactiveDataDriverContextVariable(
                    publicationService.getAllWherePublicationIsNotAttachedOrStudentDegreeIdMatch(id),
                    1);

        model.addAttribute("degrees", degreeService.getAll());
        model.addAttribute("students", studentService.getAll());
        model.addAttribute("publications", publicationsVariable);
        model.addAttribute("studentDegreeDto", studentDegreeService.getById(id));

        return "student-degree-edit-form";
    }

    @PostMapping("/edit")
    public Mono<String> editProcess(
            @RequestParam("id") Long id,
            @Valid @ModelAttribute StudentDegreeFormDto studentDegreeFormDto,
            BindingResult bindingResult,
            Model model)
    {
        if (bindingResult.hasErrors()) {
            IReactiveDataDriverContextVariable publicationsVariable =
                    new ReactiveDataDriverContextVariable(
                            publicationService.getAllWherePublicationIsNotAttachedOrStudentDegreeIdMatch(id),
                            1);

            model.addAttribute("degrees", degreeService.getAll());
            model.addAttribute("students", studentService.getAll());
            model.addAttribute("publications", publicationsVariable);
            model.addAttribute("studentDegreeDto", studentDegreeService.getById(id));

            model.addAttribute("error", "Помилка");

            return Mono.just("student-degree-edit-form");
        }
        return studentDegreeService
                .update(studentDegreeFormDto)
                .thenReturn("redirect:/student-degrees");
    }

}
