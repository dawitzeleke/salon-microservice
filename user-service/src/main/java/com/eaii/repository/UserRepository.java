package com.eaii.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eaii.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
