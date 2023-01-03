package com.osho.userservice.repositories;

import com.osho.userservice.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
