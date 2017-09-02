package com.bigsys.auth.project.db.model;

public class AppInfo {
    private Integer id;

    private String name;

    private String authTime;

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

    public String getAuthTime() {
        return authTime;
    }

    public void setAuthTime(String authTime) {
        this.authTime = authTime == null ? null : authTime.trim();
    }
}