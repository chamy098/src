package com.src.datamodel;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@RequiredArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    @ManyToMany
    private List<Movie> movies;
}
