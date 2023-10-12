package com.src.core.interfaces;

import com.src.datamodel.Movie;

import java.util.concurrent.CompletableFuture;

public interface IMovieService {
    CompletableFuture<Movie> save(Movie movie);
}
