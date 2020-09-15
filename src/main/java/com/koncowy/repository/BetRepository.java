package com.koncowy.repository;

import com.koncowy.model.BetApp;
import com.koncowy.model.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BetRepository extends JpaRepository<BetApp, Long> {

  Page<BetApp> findAllByUser(UserApp user, Pageable pageable);

}
