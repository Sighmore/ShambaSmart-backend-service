package com.ShambaSmart.ShambaSmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);

    User findByUsername(String username);
}
