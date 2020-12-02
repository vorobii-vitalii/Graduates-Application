package com.pnu.db.Graduates.Application.controller;

import com.pnu.db.Graduates.Application.dto.DegreeDto;
import com.pnu.db.Graduates.Application.service.DegreeService;
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
@RequestMapping("/degree")
@AllArgsConstructor
public class DegreeController {
    private final DegreeService degreeService;

    @GetMapping
    public String showAll(final Model model) {
        populateModel(model);
        return "degrees";
    }

    @PostMapping
    public Mono<String> processAddRequest (
                @Valid @ModelAttribute DegreeDto degreeDto,
                BindingResult bindingResult,
                Model model
    ) {
        populateModel(model);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Помилка вводу");
            return Mono.just("degrees");
        }
        return degreeService.save(degreeDto).thenReturn("degrees");
    }

    @GetMapping("/delete")
    public Mono<String> delete(@RequestParam("id") Integer id) {
        return degreeService
                .deleteById(id)
                .thenReturn("redirect:/degree");
    }

    private void populateModel(Model model) {
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(degreeService.getAll(), 1);
        model.addAttribute("degrees", reactiveDataDrivenMode);
        model.addAttribute("degreeToAdd", new DegreeDto());
    }

}
