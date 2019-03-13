package com.mandalart_server.dao;

import com.mandalart_server.model.Mandalart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MandalartRepository extends JpaRepository<Mandalart, Long> {

    @Query(value = "SELECT * FROM mandalart WHERE user_id_fk LIKE ?1 ", nativeQuery = true)
    List<Mandalart> findAllByUserId(Long userId);

    Optional<Mandalart> findById(Long mandalartId);
}
