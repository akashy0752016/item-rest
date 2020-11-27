package com.cloud.item.repository;

import com.cloud.item.model.OriginalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginalDataRepository extends JpaRepository<OriginalData, Integer> {
}
