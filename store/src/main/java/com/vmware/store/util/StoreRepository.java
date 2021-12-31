package com.vmware.store.util;

import com.vmware.store.model.Catalog;
import com.vmware.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByName(String name);
    boolean existsByName(String name);
}
