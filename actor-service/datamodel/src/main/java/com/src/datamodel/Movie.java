package com.src.datamodel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imdbID")
    private Long imdbID;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Release year is required")
    private int releaseYear;

    private String description;

}