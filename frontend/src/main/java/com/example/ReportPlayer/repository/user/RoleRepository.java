package com.example.ReportPlayer.repository.user;

import com.example.ReportPlayer.models.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByType(String type);
}
