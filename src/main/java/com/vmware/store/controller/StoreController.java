package com.vmware.store.controller;

import com.vmware.store.dto.Message;
import com.vmware.store.dto.store.StoreData;
import com.vmware.store.model.User;
import com.vmware.store.services.StoreService;
import com.vmware.store.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Resource(name = "storeService")
    private StoreService storeService;
    @Resource(name = "userService")
    private UserService userService;

    @PostMapping("/store")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> addStore(@RequestBody StoreData storeData) {
        Message message = storeService.addStore(storeData,getUser());
        return message.isSuccess() ? ResponseEntity.ok(message.getResponse()) : ResponseEntity.badRequest().body(message.getResponse());
    }

    @PostMapping("/owner")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> addOwner(@RequestBody StoreData storeData){
        Message message = storeService.addOwner(storeData, getUser());
        return message.isSuccess() ? ResponseEntity.ok(message.getResponse()) : ResponseEntity.badRequest().body(message.getResponse());
    }

    @PostMapping("/catalogs")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> addCatalog(@RequestBody StoreData storeData) {
        Message message = storeService.addCatalog(storeData,getUser());
        return message.isSuccess() ? ResponseEntity.ok(message.getResponse()) : ResponseEntity.badRequest().body(message.getResponse());
    }

    @PostMapping("/items")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> addItems(@RequestBody StoreData storeData){
        Message message = storeService.addCatalogItems(storeData, getUser());
        return message.isSuccess() ? ResponseEntity.ok(message.getResponse()) : ResponseEntity.badRequest().body(message.getResponse());
    }

    @GetMapping("/items")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getItems() {
        return ResponseEntity.ok(storeService.getAllItems().getResponse());
    }

    @GetMapping("/catalogs")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getCatalogs() {
        return ResponseEntity.ok(storeService.getAllCatalogs().getResponse());
    }

    @GetMapping("/catalog/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('OWNER')")
    public ResponseEntity<?> getCatalog(@PathVariable Long id) {
        Message message = storeService.getCatalogById(id);
        return message.isSuccess() ? ResponseEntity.ok(message.getResponse()) : ResponseEntity.badRequest().body(message.getResponse());
    }

    @DeleteMapping("/catalogs")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> deleteCatalog(@RequestBody StoreData storeData) {
        Message message = storeService.deleteCatalogs(storeData, getUser());
        return message.isSuccess() ? ResponseEntity.ok(message.getResponse()) : ResponseEntity.badRequest().body(message.getResponse());
    }

    @PostMapping("/update/items")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<?> updateItem(@RequestBody StoreData storeData){
        Message message = storeService.updateItems(storeData, getUser());
        return message.isSuccess() ? ResponseEntity.ok(message.getResponse()) : ResponseEntity.badRequest().body(message.getResponse());
    }

    @PostMapping("/purchase")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> purchaseItems(@RequestBody List<Long[]> purchaseData){
        Message message = storeService.purchaseItems(purchaseData, getUser());
        return message.isSuccess() ? ResponseEntity.ok(message.getResponse()) : ResponseEntity.badRequest().body(message.getResponse());
    }

    private User getUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUser(userDetails.getUsername());
    }

}
