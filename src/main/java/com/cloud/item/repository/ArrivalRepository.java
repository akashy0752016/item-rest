package com.cloud.item.repository;

import com.cloud.item.model.Arrival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArrivalRepository extends JpaRepository<Arrival, Integer> {
    List<Arrival> findAll();
    Arrival findAllById(int id);
}
