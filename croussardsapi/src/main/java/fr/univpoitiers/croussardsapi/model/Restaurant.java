package fr.univpoitiers.croussardsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resto")
    private Long idResto;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "type_resto")
    private String typeResto;

    @OneToMany(mappedBy = "restaurant")
    @JsonIgnoreProperties("restaurant")
    private List<Review> reviews = new ArrayList<>();

}