package com.src.core.interfaces;

import com.src.datamodel.Movie;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IMovieService {
    CompletableFuture<List<Movie>> getAllMovies();

    CompletableFuture<Page<Movie>> getMoviesByPage(int size, int page);
    CompletableFuture<Movie> save(Movie movie);

    CompletableFuture<Movie> getById(Long imdbID);

    CompletableFuture<Movie> update(Movie movie);

    CompletableFuture<Boolean> delete(Long imdbID);
}
