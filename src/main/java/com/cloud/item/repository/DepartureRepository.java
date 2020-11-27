package com.cloud.item.repository;

import com.cloud.item.model.Departure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, Integer> {
    List<Departure> findAll();
    Departure findAllById(int id);
}
