package com.bigsys.auth.project.model;

import java.util.List;

public class RoleMenus {
    private String id;
    private List<String> links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
