package com.cloud.item.repository;

import com.cloud.item.model.Users;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=NONE)
class UsersRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsersRepository usersRepository;

    private Users users;

    @BeforeEach
    public void setUp() {
        users = new Users();
        users.setName("BigG");
        users.setAddress("Glandale road 4");
        users.setCity("Glandale");
        users.setState("California");
        users.setZipCode("AD2123");
        users.setCardType("master");
        users.setCardNumber("2864528645765429346");
        users.setCardExpirationMonth(6);
        users.setCardExpirationYear(2020);
        users.setCardNameOnCard("BigGInDaHouse");
        users.setFlightId(12);
    }

    @Test
    void whenFindAll() {
        entityManager.persist(users);
        entityManager.flush();

        //when
        List<Users> usersList = usersRepository.findAll();

        //then
        assertThat(usersList.size()).isEqualTo(1);
        assertThat(usersList.get(0)).isEqualTo(users);
    }

    @Test
    void whenFindAllById() {
        entityManager.persist(users);
        entityManager.flush();

        //when
        Users allById = usersRepository.findAllById(users.getId());

        //then
        assertThat(allById.getAddress()).isEqualTo(users.getAddress());
    }

    @Test
    void whenDeleteById() {
        entityManager.persist(users);
        entityManager.flush();

        //when
        usersRepository.deleteById(users.getId());
        List<Users> usersList = usersRepository.findAll();

        //then
        assertThat(usersList.size()).isEqualTo(0);
    }
}