package edu.sjsu.cmpe275.project.domain;
import javax.persistence.*;
import java.util.*;

/**
 * Created by Liping on 11/19/15.
 */

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "description")
    private String description;

    public User(int id, String username, String email, String description) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.description = description;
    }

    public User(String username, String email, String description) {
        this.username = username;
        this.email = email;

        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
