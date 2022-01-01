package com.onlinestore.services;

import com.onlinestore.dto.Message;
import com.onlinestore.model.User;
import com.onlinestore.dto.store.StoreData;

import java.util.List;

public interface StoreService {
    Message addStore(StoreData storeData, User user);
    Message addOwner(StoreData storeData, User user);
    Message addCatalog(StoreData storeData, User user);
    Message addCatalogItems(StoreData storeData, User user);
    Message getCatalogById(Long catalogId);
    Message getAllCatalogs();
    Message getAllItems();
    Message deleteCatalogs(StoreData catalogData, User user);
    Message updateItems(StoreData storeData, User user);
    Message purchaseItems(List<Long[]> purchaseData, User user);
}
