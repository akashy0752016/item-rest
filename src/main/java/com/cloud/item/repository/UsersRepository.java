package com.cloud.item.repository;

import com.cloud.item.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Users> findAll();
    Users findAllById(int id);
    @Transactional
    void deleteById(int id);
}
