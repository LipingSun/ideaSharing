package edu.sjsu.cmpe275.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Idea> ideas = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserReadIdea> userReadIdeas = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserLikedIdea> userLikedIdeas = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(Set<Idea> ideas) {
        this.ideas = ideas;
    }

    public Set<UserReadIdea> getUserReadIdeas() {
        return userReadIdeas;
    }

    public void setUserReadIdeas(Set<UserReadIdea> userReadIdeas) {
        this.userReadIdeas = userReadIdeas;
    }

    public Set<UserLikedIdea> getUserLikedIdeas() {
        return userLikedIdeas;
    }

    public void setUserLikedIdeas(Set<UserLikedIdea> userLikedIdeas) {
        this.userLikedIdeas = userLikedIdeas;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + id +
            ", username='" + username + "'" +
            '}';
    }
}
