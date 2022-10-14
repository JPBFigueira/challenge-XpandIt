package com.challengexpandit.service;

import com.challengexpandit.model.dto.MoviesRequest;
import com.challengexpandit.model.dto.MoviesResponse;
import com.challengexpandit.model.resource.MovieResource;
import com.challengexpandit.repository.MoviesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class MoviesServiceTest {

    private MoviesService classUnderTest;
    private MoviesRepository mockMoviesRepository;

    @BeforeEach
    void setUp() {
        mockMoviesRepository = mock(MoviesRepository.class);
        classUnderTest = new MoviesService(mockMoviesRepository);
    }

    @Test
    void createMovie() {
        //Prep
        MoviesRequest request = new MoviesRequest(createMovieResource());
        //Execution
        MoviesResponse response = classUnderTest.createMovie(request);
        //Verification
        assertEquals("Success inserting new Movie on DB!", response.getResponse());
    }

    private MovieResource createMovieResource() {
        MovieResource movieResource = new MovieResource();
        movieResource.setTitle("Title");
        movieResource.setRank(1);
        movieResource.setRevenue(BigDecimal.TEN);
        movieResource.setLaunchDate(new Date());
        return movieResource;
    }
}