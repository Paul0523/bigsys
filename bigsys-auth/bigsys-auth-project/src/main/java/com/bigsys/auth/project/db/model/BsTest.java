package com.bigsys.auth.project.db.model;

public class BsTest {
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "BsTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}