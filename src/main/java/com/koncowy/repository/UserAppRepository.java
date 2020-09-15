package com.koncowy.repository;

import com.koncowy.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {


    @Query(value = "select user from UserApp user where user.login = ?1")
    Optional<UserApp> findUserByUsername(String s);
}
