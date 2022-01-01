package com.onlinestore.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "catalog")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itemsList = new ArrayList<>();

    public Catalog(){}

    public Catalog(Long id, List<Item> itemsList) {
        this.id = id;
        this.itemsList = itemsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }


    public Catalog merge(Catalog other) {
        itemsList.addAll(other.itemsList);
        return this;
    }

}
