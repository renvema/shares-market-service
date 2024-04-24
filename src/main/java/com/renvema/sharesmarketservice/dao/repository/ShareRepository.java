package com.renvema.sharesmarketservice.dao.repository;

import com.renvema.sharesmarketservice.dao.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
}
