package com.challengexpandit.controller;


import com.challengexpandit.model.dto.MoviesRequest;
import com.challengexpandit.model.dto.MoviesResponse;
import com.challengexpandit.service.MoviesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@RestController("Movies Controller")
@RequestMapping(value = MoviesController.MOVIES_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MoviesController {
    static final String MOVIES_URL = "/movies";
    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @Operation(summary = "Create Movie")
    @PostMapping(value = "/create")
    public MoviesResponse createMovie(@RequestBody MoviesRequest request) {
        return moviesService.createMovie(request);
    }

    @Operation(summary = "Get Movie")
    @GetMapping(value = "/get")
    public MoviesResponse getMovie(@NotBlank @RequestParam(name = "title") String movieName) {
        return moviesService.getMovieByName(movieName);
    }

    @Operation(summary = "Update Movie")
    @PutMapping(value = "/update")
    public MoviesResponse updateMovie(@Valid @RequestBody MoviesRequest request) {
        return moviesService.updateMovieByName(request);
    }

    @Operation(summary = "Delete Movie")
    @DeleteMapping(value = "/delete")
    public MoviesResponse deleteMovie(@NotBlank @RequestParam(name = "title") String movieName) {
        return moviesService.deleteMovieByName(movieName);
    }

    @Operation(summary = "Get Movies List by order")
    @GetMapping(value = "/get-list-by-order")
    public MoviesResponse getMoviesListByOrder() {
        return moviesService.getMoviesListByOrder();
    }
}
