package com.pnu.db.Graduates.Application.controller;

import com.pnu.db.Graduates.Application.dto.DegreeDto;
import com.pnu.db.Graduates.Application.dto.PublicationDto;
import com.pnu.db.Graduates.Application.service.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Controller
@RequestMapping("/publications")
@AllArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;

    @GetMapping
    public String getAll(Model model) {
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(publicationService.getAll(), 1);
        model.addAttribute("publications", reactiveDataDrivenMode);
        return "publications";
    }

    @PostMapping
    public Mono<String> processForm (
            @Valid @ModelAttribute PublicationDto publicationDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Помилка вводу");
            model.addAttribute("publicationDto", publicationDto);
            return Mono.just("publication_form");
        }
        return publicationService.add(publicationDto).thenReturn("redirect:/publications");
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("publicationDto", new PublicationDto());
        return "publication_form";
    }

    @GetMapping("/delete")
    public Mono<String> delete(@RequestParam("id") Long id, Model model) {
        return publicationService
                .deleteById(id)
                .thenReturn("redirect:/publications");
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("publicationDto", publicationService.getById(id));
        return "publication_form";
    }

}
