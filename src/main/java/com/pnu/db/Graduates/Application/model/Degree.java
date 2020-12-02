package com.pnu.db.Graduates.Application.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table( "degree")
@Data
public class Degree {

    @Id
    @Column("degree_id")
    private Integer id;

    @Column("name")
    private String name;

}
