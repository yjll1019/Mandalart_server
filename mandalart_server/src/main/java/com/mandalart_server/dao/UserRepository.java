package com.mandalart_server.dao;

import com.mandalart_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
