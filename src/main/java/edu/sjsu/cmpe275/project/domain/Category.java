package edu.sjsu.cmpe275.project.domain;

import javax.persistence.*;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    //@OneToMany(mappedBy = "category")
    //@JsonIgnore
    //private Set<Idea> ideas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public Set<Idea> getIdeas() {
    //    return ideas;
    //}
    //
    //public void setIdeas(Set<Idea> ideas) {
    //    this.ideas = ideas;
    //}

}
