package com.src.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.src.core.exceptions.NotFoundException;
import com.src.datamodel.Movie;
import com.src.service.MovieService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;


    private Movie createTestMovie() {
        Movie movie = new Movie();
        movie.setTitle("Test movie");
        movie.setReleaseYear(2021);
        movie.setDescription("Some desc here");
        return movie;
    }

    @Test
    public void testSaveMovie() throws Exception  {
        Movie movie = createTestMovie();
        Movie savedMovie = movieService.save(movie).get();

        // Check returned values
        assertThat(savedMovie.getTitle(), containsString(movie.getTitle()));
        assertThat(savedMovie.getReleaseYear(), equalTo(movie.getReleaseYear()));
        assertThat(savedMovie.getDescription(), containsString(movie.getDescription()));

    }

    @Test
    public void testUpdateMovie() throws Exception  {
        Movie movie = movieService.save(createTestMovie()).get();
        movie.setTitle("Updated title");

        //Update the movie
        Movie updatedMovie = movieService.update(movie).get();

        // Check returned values
        assertThat(updatedMovie.getTitle(), containsString(movie.getTitle()));
        assertThat(updatedMovie.getReleaseYear(), equalTo(movie.getReleaseYear()));
        assertThat(updatedMovie.getDescription(), containsString(movie.getDescription()));

    }

    @Test
    public void testDelete() throws Exception  {
        Movie movie = movieService.save(createTestMovie()).get();
        Boolean removed = movieService.delete(movie.getImdbID()).get();
        // Check returned values
        assertTrue(removed);
    }

    @Test
    public void testGetAllMovies() throws Exception  {
        Movie movie = movieService.save(createTestMovie()).get();
        List<Movie> movieList = movieService.getAllMovies().get();
        // Check returned values
        assertFalse(movieList.isEmpty());
    }

    @Test
    public void getMoviesByPage() throws Exception  {
        movieService.save(createTestMovie());
        Page<Movie> movieList = movieService.getMoviesByPage(10,0).get();
        // Check returned values
        assertFalse(movieList.isEmpty());
    }

    @Test
    public void testUpdateNonExistentMovie() {
        Movie movie = createTestMovie();
        movie.setImdbID(12345L); // Add a non-existent movie ID
        // Attempt to update the non-existent movie
        try {
            movieService.update(movie).get();
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            // Check if the exception is the expected type
            assertThat(e, is(instanceOf(NotFoundException.class)));
        }
    }
}
