package com.src.datamodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.src.datamodel.Actor;
import lombok.Setter;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int releaseYear;
    private String description;

    @ManyToMany
    private List<Actor> actors;
}
