package com.pnu.db.Graduates.Application.dto;

import com.pnu.db.Graduates.Application.validation.UniqueDegreeConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DegreeDto {

    private Long id;

    @NotNull
    @UniqueDegreeConstraint
    @NotBlank
    private String name;
}
