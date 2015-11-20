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

    /* 注意这里以后需要加person<-->idea,@OneToMany(mappedBy = "user")
     @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)  //what is this function about?
    @JoinColumn(name = "org_id")
    private Organization org;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //what is this function about?
    @JoinTable(name = "FRIENDSHIP",
            joinColumns = {@JoinColumn(name = "person1_id", referencedColumnName = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "person2_id", referencedColumnName = "person_id")})
    private List<Person> friends;
     */


    public User(int id, String username, String email, String description) {
        this.id = id;
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
