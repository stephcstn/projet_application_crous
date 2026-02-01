package fr.univpoitiers.croussardsweb.model;

import lombok.Data;
import java.util.List;

@Data
public class Student {

    private Long numStudent;

    private String lastName;

    private String firstName;

    private String mail;

    private String pwdHash;

    private String username;

    private List<Review> reviews;

}