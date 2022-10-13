package com.challengexpandit.model.dto;

import com.challengexpandit.model.resource.MovieResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoviesResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<MovieResource> movieList;
    private String response;

    public MoviesResponse(String response) {
        this.response = response;
    }
}
