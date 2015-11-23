package edu.sjsu.cmpe275.project.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A UserReadIdea.
 */
@Entity
@Table(name = "user_read_idea")
public class UserReadIdea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "time")
    private ZonedDateTime time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Person user;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person person) {
        this.user = person;
    }

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserReadIdea userReadIdea = (UserReadIdea) o;
        return Objects.equals(id, userReadIdea.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserReadIdea{" +
            "id=" + id +
            ", time='" + time + "'" +
            '}';
    }
}
