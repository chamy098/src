package com.src.application.controllers;

import com.src.core.interfaces.IMovieService;
import com.src.datamodel.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {
    private final IMovieService movieService;


    @PostMapping("save")
    public CompletableFuture<Movie> save(@RequestBody Movie movie) {
        return this.movieService.save(movie);
    }
}
