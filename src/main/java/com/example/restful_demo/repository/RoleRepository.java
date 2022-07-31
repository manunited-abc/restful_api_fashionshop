package com.example.restful_demo.repository;

import com.example.restful_demo.constant.ERole;
import com.example.restful_demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}
