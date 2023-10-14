package com.src.service;

import com.src.core.exceptions.NotFoundException;
import com.src.core.interfaces.IMovieService;
import com.src.datamodel.Movie;
import com.src.persistence.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;

    @Override
    public CompletableFuture<List<Movie>> getAllMovies() {
        return CompletableFuture.supplyAsync(this.movieRepository::findAllMoviesWithActors);
    }


    @Override
    public CompletableFuture<Page<Movie>> getMoviesByPage(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> movies = this.movieRepository.findAll(pageable);
        return CompletableFuture.supplyAsync(() ->  movies);
    }

    @Override
    public CompletableFuture<List<Movie>> search(String word) {
        List<Movie> movies = this.movieRepository.search(word);
        return CompletableFuture.supplyAsync(() -> movies);
    }

    @Override
    public CompletableFuture<Movie> save(Movie movie) {
        return CompletableFuture.supplyAsync(() -> this.movieRepository.save(movie));
    }

    @Override
    public CompletableFuture<Movie> getById(Long imdbID) {
        Movie existingMovie = this.movieRepository.findById(imdbID).orElse(null);
        if(existingMovie == null) {
            throw new NotFoundException("Movie not found");
        }
        return CompletableFuture.supplyAsync(() -> existingMovie);
    }

    @Override
    public CompletableFuture<Movie> update(Movie movie) {
        //Check if the movie exists
        Movie existingMovie = this.movieRepository.findById(movie.getImdbID()).orElse(null);
        if(existingMovie == null) {
            throw new NotFoundException("Movie not found");
        }
        return CompletableFuture.supplyAsync(() -> this.movieRepository.save(movie));

    }

    @Override
    public CompletableFuture<Boolean> delete(Long imdbID) {
        //Check if the movie exists
        Movie existingMovie = this.movieRepository.findById(imdbID).orElse(null);
        if(existingMovie == null) {
            throw new NotFoundException("Movie not found");
        }

        return CompletableFuture.supplyAsync(() -> {
            this.movieRepository.deleteById(imdbID);
            return true;
        });
    }
}
