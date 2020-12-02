package com.pnu.db.Graduates.Application.model;

import lombok.Data;


import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("student")
@Data
public class Student {

    @Id
    @Column("student_id")
    private Long id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("current_position" )
    private String currentPosition;

}
