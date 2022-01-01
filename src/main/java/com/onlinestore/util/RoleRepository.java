package com.onlinestore.util;

import java.util.Optional;

import com.onlinestore.model.ERole;
import com.onlinestore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    Boolean existsByName(ERole name);
}