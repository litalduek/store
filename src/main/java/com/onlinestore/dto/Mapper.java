package com.onlinestore.dto;

import com.onlinestore.dto.store.CatalogData;
import com.onlinestore.dto.store.ItemData;
import com.onlinestore.dto.store.StoreData;
import com.onlinestore.dto.user.JwtData;
import com.onlinestore.dto.user.SignupData;
import com.onlinestore.model.*;
import com.onlinestore.security.services.UserDetailsImpl;
import com.vmware.store.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public Store toStore(StoreData storeData, User user){
        Store store = new Store(storeData.getName());
        store.getOwnerList().add(user);
        return store;
    }

    public StoreData toStoreDto(Store store){
        List<CatalogData> catalogsData = new ArrayList<>();
        store.getCatalogsList().forEach(catalog -> {
            catalogsData.add(toCatalogDto(catalog));
        });
        return new StoreData(store.getName(), catalogsData);
    }

    public JwtData toJwtDto(final UserDetailsImpl user, String jwt) {
        List<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        return new JwtData(jwt, user.getId(),user.getUsername(),user.getEmail(),roles);
    }

    public Payment toPayment(final SignupData userData){
        return new Payment(userData.getPaymentFullName(),userData.getPaymentExpDate(),userData.getPaymentCardNum());
    }

    public User toUser(final SignupData userData) {
        return new User(userData.getFirstName(), userData.getLastName(),
                userData.getUserName(), userData.getEmail(), userData.getPassword(),toPayment(userData), toRole(userData));
    }

    public Set<Role> toRole(final SignupData userData){
        Set<Role> roles = new HashSet<>();

        if (userData.getRole() == null) {
            Role userRole = new Role(ERole.ROLE_CUSTOMER);
            roles.add(userRole);
        }
        else{
            userData.getRole().forEach(role -> {
                if(role.equals("owner")) {
                    Role ownerRole = new Role(ERole.ROLE_OWNER);
                    roles.add(ownerRole);
                }
                else if(role.equals( "customer")){
                    Role userRole = new Role(ERole.ROLE_CUSTOMER);
                    roles.add(userRole);
                }
            });
        }
        return roles;
    }

    public ItemData toItemDto(final Item item){
        return new ItemData(item.getId(),item.getName(),item.getPrice(),item.getStock());
    }

    public Item toItem(ItemData itemData){
        return  new Item(itemData.getId(),itemData.getName(),itemData.getPrice(),itemData.getStock());
    }

    public CatalogData toCatalogDto(Catalog catalog) {
        List<ItemData> itemsData = new ArrayList<>();
        catalog.getItemsList().forEach(item -> {
            itemsData.add(toItemDto(item));
        });
        return new CatalogData(catalog.getId(),itemsData);
    }

    public Catalog toCatalog(CatalogData catalogData) {
        List<Item> items = new ArrayList<>();
        catalogData.getItemsList().forEach(itemData -> {
            items.add(toItem(itemData));
        });
        return new Catalog(catalogData.getId(),items);
    }
}