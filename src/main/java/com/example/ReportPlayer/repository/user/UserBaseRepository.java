package com.example.ReportPlayer.repository.user;

import com.example.ReportPlayer.enumerated.Server;
import com.example.ReportPlayer.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBaseRepository extends JpaRepository<User, Long>{
    User findByUsername(String s);
    User findByEmail(String s);
}
