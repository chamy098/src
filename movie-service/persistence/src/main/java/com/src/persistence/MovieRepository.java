package com.src.persistence;

import com.src.datamodel.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT DISTINCT m FROM Movie m LEFT JOIN FETCH m.actors")
    List<Movie> findAllMoviesWithActors();
}
