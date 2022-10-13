package com.challengexpandit.service;


import com.challengexpandit.model.dto.MoviesRequest;
import com.challengexpandit.model.dto.MoviesResponse;
import com.challengexpandit.model.entity.Movie;
import com.challengexpandit.model.resource.MovieResource;
import com.challengexpandit.repository.MoviesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MoviesService {

    private static final Logger LOG = LoggerFactory.getLogger(MoviesService.class);
    private final MoviesRepository moviesRepository;
    private final ObjectMapper objectMapper;

    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
        this.objectMapper = new ObjectMapper();
    }

    public MoviesResponse createMovie(MoviesRequest request) {
        MovieResource movieResource = request.getMovieResource();
        if (isMovieResourceValid(movieResource)) {
            try {
                Movie movie = objectMapper.convertValue(movieResource, Movie.class);
                moviesRepository.insert(movie);
                return new MoviesResponse("Success inserting new Movie on DB!");
            } catch (Exception e) {
                LOG.error(e.getMessage());
                return new MoviesResponse(e.getMessage());
            }
        } else {
            return new MoviesResponse("Invalid Movie Resource!");
        }
    }

    public MoviesResponse getMovieByName(String movieName) {
        try {
            Optional<Movie> movie = getMovieById(movieName);
            return movie.map(value -> new MoviesResponse(Collections.singletonList(objectMapper.convertValue(value, MovieResource.class)), "Success!")).orElseGet(() -> new MoviesResponse("Movie not in DB!"));
        } catch (Exception e) {
            LOG.debug(e.getMessage());
            return new MoviesResponse(e.getMessage());
        }
    }

    private Optional<Movie> getMovieById(String movieName) {
        return Optional.of(moviesRepository.findById(movieName)).orElse(null);
    }

    public MoviesResponse updateMovieByName(MoviesRequest request) {
        MovieResource movieResource = request.getMovieResource();
        Optional<Movie> movie = getMovieById(movieResource.getTitle());
        if (movie.isPresent() && isMovieResourceValid(movieResource)) {
            try {
                moviesRepository.save(objectMapper.convertValue(movieResource, Movie.class));
                return new MoviesResponse("Success saving Movie with new Data on DB!");
            } catch (Exception e) {
                LOG.error(e.getMessage());
                return new MoviesResponse(e.getMessage());
            }
        }
        return new MoviesResponse("Movie not found or invalid movie resource provided!");
    }

    public MoviesResponse deleteMovieByName(String movieName) {
        try {
            moviesRepository.deleteById(movieName);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new MoviesResponse(e.getMessage());
        }
        return new MoviesResponse("Success!");
    }

    public MoviesResponse getMoviesListByOrder() {
        List<Movie> moviesListByOrder = moviesRepository.findAll(Sort.by(Sort.Direction.ASC, "launchDate"));
        return MoviesResponse
                .builder()
                .movieList(moviesListByOrder
                        .parallelStream()
                        .map(movie -> objectMapper.convertValue(movie, MovieResource.class))
                        .collect(Collectors.toList()))
                .response("Sucess!")
                .build();
    }

    private boolean isMovieResourceValid(MovieResource movieResource) {

        if (movieResource.getRank() < 0 || movieResource.getRank() > 10 || ObjectUtils.isEmpty(movieResource.getRank())) {
            LOG.debug("Rank field must be between 1 and 10.");
            return false;
        } else if (ObjectUtils.isEmpty(movieResource.getRevenue())) {
            LOG.debug("Revenue must not be empty");
            return false;
        } else if (ObjectUtils.isEmpty(movieResource.getLaunchDate())) {
            LOG.debug("Launch Date must not be empty");
            return false;
        } else {
            return true;
        }
    }
}
