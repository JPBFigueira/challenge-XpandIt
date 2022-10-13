package com.challengexpandit.model.dto;

import com.challengexpandit.model.resource.MovieResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * This model is used for both create and update requests.
 * Custom validation is applied for each case.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoviesRequest implements Serializable {
    @NotNull
    @Valid
    private MovieResource movieResource;

    @Override
    public String toString() {
        return "MoviesRequest{" +
                "MovieResource=" + movieResource +
                '}';
    }
}
