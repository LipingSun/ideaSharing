package edu.sjsu.cmpe275.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Idea.
 */
@Entity
@Table(name = "idea")
public class Idea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "problem", nullable = false)
    private String problem;

    @NotNull
    @Column(name = "solution")
    private String solution;

    @Column(name = "datetime")
    private ZonedDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "idea")
    @JsonIgnore
    private Set<UserReadIdea> userReadIdeas = new HashSet<>();

    @OneToMany(mappedBy = "idea")
    @JsonIgnore
    private Set<UserLikedIdea> userLikedIdeas = new HashSet<>();

    @OneToMany(mappedBy = "idea")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Idea idea = (Idea) o;
        return Objects.equals(id, idea.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Idea{" +
            "id=" + id +
            ", title='" + title + "'" +
            '}';
    }

    public ZonedDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(ZonedDateTime datetime) {
        this.datetime = datetime;
    }

    public int getLikeCount() {
        return userLikedIdeas.size();
    }
}
