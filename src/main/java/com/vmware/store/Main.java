//package com.vmware.store;
//
//import com.vmware.store.entity.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//
//        Item item1 = new Item("Apple", ItemType.FOOD, 0.99, 10);
//        Item item2 = new Item("Pen", ItemType.OFFICE,1.5, 20);
//        Item item3 = new Item("Marker", ItemType.OFFICE, 2, 50);
//        Item item4 = new Item("Shampoo", ItemType.TOILETRIES, 9.99, 10);
//        List<Item> items1 = new ArrayList<>();
//        items1.add(item1);
//        items1.add(item2);
//        items1.add(item3);
//        items1.add(item4);
//
//        Catalog catalog = new Catalog(items1);
//
//        List<Item> items2 = new ArrayList<>();
//        items2.add(item2);
//        items2.add(item4);
//
//
//        Customer customer1 = new Customer(398908390, "Malka", "Duek");
//        Customer customer2 = new Customer(398847574, "Avi", "Duek");
//
//        Owner owner1 = new Owner("Lital","Fine");
//        Owner owner2 = new Owner("Zafrir","Fine");
//
//        List<Owner> owners = new ArrayList<>();
//        owners.add(owner1);
//        owners.add(owner2);
//
//        Store store = new Store("Shufersal",owners);
//
//        store.addCatalog(catalog);
//        store.addItems(10,items2);
//        store.addItems(1,items2);
//        store.removeItems(2,items2);
//        store.removeItems(1,items1);
//        store.removeCatalog(1);
//
//    }
//}
