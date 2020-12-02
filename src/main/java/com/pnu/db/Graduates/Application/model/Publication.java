package com.pnu.db.Graduates.Application.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("publications")
@Data
public class Publication {

    @Id
    @Column(value = "publication_id")
    private Long id;

    @Column("title")
    private String title;

    @Column("url")
    private String url;

}
