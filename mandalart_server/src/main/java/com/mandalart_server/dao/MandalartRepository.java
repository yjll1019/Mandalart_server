package com.mandalart_server.dao;

import com.mandalart_server.model.Mandalart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MandalartRepository extends JpaRepository<Mandalart, Long> {

}
