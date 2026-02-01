package fr.univpoitiers.croussardsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

/**
 * Représente un avis (review) laissé par un étudiant pour un restaurant.
 */
@Entity
@Table(name = "reviews")
@Data // Fournit les getters, setters, etc. (Lombok)
@NoArgsConstructor // Constructeur sans argument (Lombok)
@AllArgsConstructor // Constructeur avec tous les arguments (Lombok)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private Long idReview;

    @ManyToOne
    @JoinColumn(name = "id_student")
    @JsonIgnoreProperties({"reviews"})
    private Student student;


    @ManyToOne
    @JoinColumn(name = "id_resto")
    @JsonIgnoreProperties({"reviews"})
    private Restaurant restaurant;

    @Min(1)
    @Max(5)
    @Column(name = "nb_stars")
    private int nbStars;

    @Column(name = "comments")
    private String comments;

    @Column(name = "date_publi")
    private LocalDate datePubli;

}