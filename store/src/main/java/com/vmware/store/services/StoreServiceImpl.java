package com.vmware.store.services;

import com.vmware.store.dto.Mapper;
import com.vmware.store.dto.Message;
import com.vmware.store.dto.store.CatalogData;
import com.vmware.store.dto.store.ItemData;
import com.vmware.store.dto.store.StoreData;
import com.vmware.store.model.*;
import com.vmware.store.util.CatalogRepository;
import com.vmware.store.util.ItemRepository;
import com.vmware.store.util.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

    private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);
    @Autowired
    private CatalogRepository catalogRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private StoreRepository storeRepository;

    private Mapper mapper = new Mapper();


    @Override
    public Message addStore(StoreData storeData, User user) {
        if(storeRepository.existsByName(storeData.getName())){
            return new Message("Error: Store name is already taken!", false);
        }
        Store store;
        try{
            store = storeRepository.save(mapper.toStore(storeData,user));
        }catch(RuntimeException e){
            logger.error(e.getMessage());
            return new Message("Error while creating a store", false);
        }
        return new Message(mapper.toStoreDto(store),true);
    }

    @Override
    public Message addOwner(StoreData storeData, User user) {
        Store storeModel;
        try {
            storeModel = findStoreByName(storeData.getName());
            if(storeModel.getOwnerList().contains(user)){
                return new Message("Error: user is already an owner.",false);
            }
            else{
                storeModel.getOwnerList().add(user);
                storeRepository.save(storeModel);
            }
        }catch (RuntimeException e){
            logger.error(e.getMessage());
            return new Message("Error while adding an owner", false);
        }
        return new Message(mapper.toStoreDto(storeModel),true);
    }

    @Override
    public Message addCatalog(StoreData storeData, User user) {
        Store storeModel;
        try {
            storeModel = findStoreByName(storeData.getName());
            if(storeModel.getOwnerList().contains(user)){
                storeData.getCatalogsList().forEach(catalogData -> {
                    Catalog catalogModel = mapper.toCatalog(catalogData);
                    catalogRepository.save(catalogModel);
                    storeModel.getCatalogsList().add(catalogModel);
                });
                storeRepository.save(storeModel);
            }
            else{
                return new Message("Error: user is not an owner of this store.",false);
            }
        }
        catch (RuntimeException e){
            return new Message("Error: store is not found.",false);
        }
        return new Message(mapper.toStoreDto(storeModel),true);
    }

    @Override
    public Message getAllItems() {
        Map<String, List<ItemData>> itemsData = new HashMap<>();
        List<Store> stores = storeRepository.findAll();
        stores.forEach(store -> {
            List<ItemData> items = new ArrayList<>();
            itemsData.put(store.getName(),items);
            store.getCatalogsList().forEach(catalog -> {
                catalog.getItemsList().forEach(item -> {
                    items.add(mapper.toItemDto(item));
                });
            });
        });
        return new Message(itemsData,true);
    }

    @Override
    public Message getAllCatalogs() {
        Map<String, List<CatalogData>> catalogsData = new HashMap<>();
        List<Store> stores = storeRepository.findAll();
        stores.forEach(store -> {
            List<CatalogData> catalogs = new ArrayList<>();
            catalogsData.put(store.getName(),catalogs);
            store.getCatalogsList().forEach(catalog -> {
                catalogs.add(mapper.toCatalogDto(catalog));
            });
        });
        return new Message(catalogsData,true);
    }

    @Override
    public Message addCatalogItems(StoreData storeData, User user){
        Store storeModel;
        try {
            storeModel = findStoreByName(storeData.getName());
            if (storeModel.getOwnerList().contains(user)) {
                storeData.getCatalogsList().forEach(catalogData -> {
                    Catalog existingCatalog = findCatalogById(catalogData.getId());
                    Catalog updatedCatalog = mapper.toCatalog(catalogData);
                    itemRepository.saveAll(updatedCatalog.getItemsList());
                    existingCatalog.merge(updatedCatalog);
                    catalogRepository.save(existingCatalog);
                });
                storeRepository.save(storeModel);
            } else {
                return new Message("Error: user is not an owner of this store.",false);
            }
        }
        catch (RuntimeException e){
            return new Message("Error: store is not found.",false);
        }
        return new Message(mapper.toStoreDto(storeModel),true);
    }

    @Override
    public Message getCatalogById(Long catalogId) {
        Catalog existingCatalog;
        try{
            existingCatalog = findCatalogById(catalogId);
        }
        catch (RuntimeException e){
            return new Message("Error: catalog is not found.",false);
        }
        return new Message(mapper.toCatalogDto(existingCatalog),true);
    }

    @Override
    public Message deleteCatalogs(StoreData storeData, User user) {
        Store storeModel;
        try {
            storeModel = findStoreByName(storeData.getName());
            if(storeModel.getOwnerList().contains(user)){
                List<Item> catalogItemsList = new ArrayList<>();
                List<Catalog> existingCatalogs = storeModel.getCatalogsList();
                List<Catalog> deleteCatalogs = new ArrayList<>();
                storeData.getCatalogsList().forEach(catalogData -> {
                            Catalog catalog = findCatalogById(catalogData.getId());
                            catalog.getItemsList().forEach(item -> catalogItemsList.add(item));
                            deleteCatalogs.add(catalog);
                        }
                );
                itemRepository.deleteAll(catalogItemsList);
                existingCatalogs.removeAll(deleteCatalogs);
                catalogRepository.deleteAll(deleteCatalogs);
            }
            else{
                return new Message("Error: user is not an owner of this store.",false);
            }
        }
        catch (RuntimeException e){
            return new Message("Error: store  or catalog is not found.",false);
        }
        return new Message(mapper.toStoreDto(storeModel),true);
    }

    public Message updateItems(StoreData storeData, User user) {
        Store storeModel;
        try {
            storeModel = findStoreByName(storeData.getName());
            if (storeModel.getOwnerList().contains(user)) {
                storeData.getCatalogsList().forEach(catalogData -> {
                    catalogData.getItemsList().forEach(updatedItem -> {
                        Item existingItem = findItemById(updatedItem.getId());
                        existingItem.merge(mapper.toItem(updatedItem));
                        itemRepository.save(existingItem);
                    });
                });
            } else {
                return new Message("Error: user is not an owner of this store.",false);
            }
        }catch (RuntimeException e){
            return new Message("Error: catalog or item is not found.",false);
        }
        return new Message("Catalog updated.",true);
    }

    @Override
    public Message purchaseItems(List<Long[]> purchaseData, User user) {
        Payment payment = user.getPayment();
        int paymentAmount = 0;
        for (Long[] purchaseItem : purchaseData) {
            Item item = findItemById(purchaseItem[0]);
            Long quantity = purchaseItem[1];
            paymentAmount += item.getPrice() * quantity;
        }
        //Charge user's credit card with payment amount and payment method..

        return new Message("Purchase completed.",true);
    }

    private Store findStoreByName(String name){
        return storeRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Store not found"));
    }

    private Catalog findCatalogById(Long id){
        return catalogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Catalog not found"));
    }

    private Item findItemById(Long id){
        return itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found"));
    }


}
