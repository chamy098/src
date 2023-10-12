package com.src.service;

import com.src.core.interfaces.IMovieService;
import com.src.datamodel.Movie;
import com.src.persistence.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;

    @Override
    public CompletableFuture<Movie> save(Movie movie) {
        return CompletableFuture.supplyAsync(() -> this.movieRepository.save(movie));
    }
}
