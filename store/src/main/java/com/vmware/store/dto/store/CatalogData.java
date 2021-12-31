package com.vmware.store.dto.store;

import java.util.ArrayList;
import java.util.List;

public class CatalogData {
    private Long id;
    private List<ItemData> itemsList = new ArrayList<>();

    public CatalogData(){}

    public CatalogData(Long id, List<ItemData> itemsList) {
        this.id = id;
        this.itemsList = itemsList;
    }

    public Long getId() {
        return id;
    }

    public List<ItemData> getItemsList() {
        return itemsList;
    }

    @Override
    public String toString() {
        return "{id : " + id +
                ", itemsList : " + itemsList.toString() + "}";
    }
}

