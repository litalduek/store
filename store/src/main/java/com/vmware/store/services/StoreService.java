package com.vmware.store.services;

import com.vmware.store.dto.Message;
import com.vmware.store.model.User;
import com.vmware.store.dto.store.ItemData;
import com.vmware.store.dto.store.StoreData;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;

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
