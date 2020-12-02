package com.pnu.db.Graduates.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {
    private Long id;

    @NotNull
    @NotEmpty
    private String title;

    private String url;
}
