package com.cloud.item.repository;

import com.cloud.item.model.Departure;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=NONE)
class DepartureRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DepartureRepository departureRepository;

    @Test
    void whenFindAll() {
        //given
        Departure firstDeparture = new Departure();
        firstDeparture.setCity("Yerevan");
        entityManager.persist(firstDeparture);
        entityManager.flush();

        Departure secondDeparture = new Departure();
        secondDeparture.setCity("Israel");
        entityManager.persist(secondDeparture);
        entityManager.flush();

        //when
        List<Departure> arrivals = departureRepository.findAll();

        //then
        assertThat(arrivals.size()).isEqualTo(2);
        assertThat(arrivals.get(0)).isEqualTo(firstDeparture);
        assertThat(arrivals.get(1)).isEqualTo(secondDeparture);
    }

    @Test
    void whenFindAllById() {
        Departure arrival = new Departure();
        arrival.setCity("Yerevan");
        entityManager.persist(arrival);
        entityManager.flush();

        //when
        Departure testArrival = departureRepository.findAllById(arrival.getId());

        //then
        assertThat(testArrival.getCity()).isEqualTo(arrival.getCity());
    }
}