package fr.univpoitiers.croussardsweb.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Restaurant {

    private Long idResto;

    private String name;

    private String address;

    private String typeResto;

    private List<Review> reviews;

}