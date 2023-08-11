package com.jforce_staj.ws.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Role.RoleType name);
}
//2 tane interface'İm var optional ve user kısmını sor, optional gerıye donut alınmazsa mantıklıymıs
