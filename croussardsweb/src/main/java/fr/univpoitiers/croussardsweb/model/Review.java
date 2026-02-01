package fr.univpoitiers.croussardsweb.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Review {

    private Long idReview;

    private Student student;

    private Restaurant restaurant;

    private int nbStars;

    private String comments;

    private LocalDate datePubli;

}
