package com.onlinestore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "store", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> ownerList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Catalog> catalogsList;

    public Store(){}

    public Store(String name) {
        this.name = name;
        this.ownerList = new ArrayList<>();
        this.catalogsList = new ArrayList<>();
    }

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

    public List<User> getOwnerList() {
        return ownerList;
    }

    public List<Catalog> getCatalogsList() {
        return catalogsList;
    }

}
