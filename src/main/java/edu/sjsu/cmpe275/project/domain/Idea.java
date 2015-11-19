package edu.sjsu.cmpe275.project.domain;

/**
 * Created by jianxin , xiaotong on 11/19/15.
 */
public class Idea {
    private int id;
    private String title;
    private String description;
    private String problem;
    private String solution;
//    private String picture;
    private String user_id;

    public Idea() {
    }

    public Idea(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

//    public String getPicture() {
//        return picture;
//    }

//    public void setPicture(String picture) {
//        this.picture = picture;
//    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String owner) {
        this.user_id = owner;
    }
}
