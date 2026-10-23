package fr.univpoitiers.croussardsweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Review {

    private Long id;

    private Student student;

    private Restaurant restaurant;

    private int nbStars;

    private String comments;

    private LocalDate datePubli;
}
