package com.src.application.controllers;

import com.src.core.interfaces.IMovieService;
import com.src.datamodel.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {
    private final IMovieService movieService;

    @GetMapping("getAllMovies")
    @Cacheable("All-movies")
    public CompletableFuture<List<Movie>> getAllMovies() {
        return this.movieService.getAllMovies();
    }

    @GetMapping("getMoviesByPage/{size}/{page}")
    public CompletableFuture<Page<Movie>> getMoviesByPage(@PathVariable int size, @PathVariable int page) {
        return this.movieService.getMoviesByPage(size, page);
    }

    @PostMapping("create")
    public CompletableFuture<Movie> create(@RequestBody Movie movie) {
        return this.movieService.save(movie);
    }

    @GetMapping("getById/{imdbID}")
    public CompletableFuture<Movie> getById(@PathVariable Long imdbID) {
        return this.movieService.getById(imdbID);
    }

    @PutMapping("update")
    public CompletableFuture<Movie> update(@RequestBody Movie movie) {
        return this.movieService.update(movie);
    }
    @DeleteMapping("delete/{imdbID}")
    public CompletableFuture<Boolean> delete(@PathVariable Long imdbID) {
        return this.movieService.delete(imdbID);
    }
}
