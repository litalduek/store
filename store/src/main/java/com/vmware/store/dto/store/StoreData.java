package com.vmware.store.dto.store;

import java.util.List;

public class StoreData {
    private Long id;
    private String name;
    private List<CatalogData> catalogsList;

    public StoreData() {
    }

    public StoreData(String name, List<CatalogData> catalogsList) {
        this.name = name;
        this.catalogsList = catalogsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CatalogData> getCatalogsList() {
        return catalogsList;
    }


    @Override
    public String toString() {
        return "{" +
                "name : '" + name + '\'' +
                ", catalogsList : " + catalogsList.toString() +
                '}';
    }
}
