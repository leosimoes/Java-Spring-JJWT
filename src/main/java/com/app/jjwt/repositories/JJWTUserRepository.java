package com.app.jjwt.repositories;

import com.app.jjwt.entities.JJWTUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JJWTUserRepository extends JpaRepository<JJWTUser, String> {

    Optional<JJWTUser> findByLogin(String login);

}
