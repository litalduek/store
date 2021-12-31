package com.vmware.store.util;

import java.util.Optional;

import com.vmware.store.model.ERole;
import com.vmware.store.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    Boolean existsByName(ERole name);
}