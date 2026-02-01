package fr.univpoitiers.croussardsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Long numStudent;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "mail")
    private String mail;

    @Column(name = "pwd_hash")
    private String pwdHash;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties("student")
    private List<Review> reviews = new ArrayList<>();

}