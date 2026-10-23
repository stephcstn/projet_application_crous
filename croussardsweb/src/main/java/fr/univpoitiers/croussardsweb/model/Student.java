package fr.univpoitiers.croussardsweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Student {

    private Long id;

    private String lastName;

    private String firstName;

    private String mail;

    private String pwdHash;

    private String username;

}