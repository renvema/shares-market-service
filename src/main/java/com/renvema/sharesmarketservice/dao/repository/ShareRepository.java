package com.renvema.sharesmarketservice.dao.repository;

import com.renvema.sharesmarketservice.dao.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {

    List<Share> findByUsername(String username);

    Optional<Share> findByUsernameAndTicker(String username, String ticker);

    Boolean existsByUsername(String username);

    Boolean existsByUsernameAndTicker(String username, String ticker);
}
