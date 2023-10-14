package com.src.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.src.application.controllers.MovieController;
import com.src.datamodel.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieController movieController;

    @Autowired
    private ObjectMapper objectMapper;

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

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/movie/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isOk())
                .andReturn();

        Movie savedMovie = (Movie) result.getAsyncResult();

        // Check returned values
        assertThat(savedMovie.getTitle(), containsString("Test movie"));
        assertThat(savedMovie.getReleaseYear(), equalTo(2021));
        assertThat(savedMovie.getDescription(), containsString("Some desc here"));

    }

    @Test
    public void testGetAllMovies() throws Exception  {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/movie/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isOk())
                .andReturn();
        Movie movie = createTestMovie();
        List<Movie> movieList = (List<Movie>) result.getAsyncResult();

        // Check returned values
        assertThat(movieList.get(0).getTitle(), containsString(movie.getTitle()));
        assertThat(movieList.get(0).getReleaseYear(), equalTo(movie.getReleaseYear()));
        assertThat(movieList.get(0).getDescription(), containsString(movie.getDescription()));

    }
}
