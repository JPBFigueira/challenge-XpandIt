package com.challengexpandit.model.resource;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MovieResource {
    @NotBlank
    private String title;
    private Date launchDate;
    private int rank;
    private BigDecimal revenue;

    @Override
    public String toString() {
        return "MovieResource{" +
                "title='" + title + '\'' +
                ", launchDate=" + launchDate +
                ", rank=" + rank +
                ", revenue=" + revenue +
                '}';
    }
}
