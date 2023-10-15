package com.src.datamodel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private Date dateOfBirth;

    @ManyToMany
    @JoinTable(
            name = "Movie_Actors",
            joinColumns = @JoinColumn(name = "movie_imdbID"),
            inverseJoinColumns = @JoinColumn(name = "actors_id")
    )
    private List<Movie> movies;
}
