package com.challengexpandit.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "movies")
@CompoundIndex(def = "{'date': 1}", name = "idx_date")
@Data
public class Movie {

    @Id
    private String title;
    private Date launchDate;
    private int rank;
    private BigDecimal revenue;
}
