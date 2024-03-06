package com.example.demo.repositories;

import com.example.demo.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissionRepository extends JpaRepository<Permission, Long> {


}
