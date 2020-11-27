package com.cloud.item.repository;

import com.cloud.item.model.Flight;
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
class FlightRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FlightRepository flightRepository;

    private Flight firstFlight;
    private Flight secondFlight;

    @BeforeEach
    public void setUp() {
        firstFlight = new Flight();
        firstFlight.setAirline("Virgin");
        firstFlight.setDepartureTime("20:00:00");
        firstFlight.setArrivalTime("23:20:00");
        firstFlight.setNumber(432);
        firstFlight.setPrice("$3000");

        secondFlight = new Flight();
        secondFlight.setAirline("Lufthansa");
        secondFlight.setDepartureTime("21:10:00");
        secondFlight.setArrivalTime("22:30:00");
        secondFlight.setNumber(532);
        secondFlight.setPrice("$4000");
    }

    @Test
    void whenFindAll() {
        //given
        entityManager.persist(firstFlight);
        entityManager.flush();

        entityManager.persist(secondFlight);
        entityManager.flush();

        //when
        List<Flight> flights = flightRepository.findAll();

        //then
        assertThat(flights.size()).isEqualTo(2);
        assertThat(flights.get(0)).isEqualTo(firstFlight);
        assertThat(flights.get(1)).isEqualTo(secondFlight);
    }

    @Test
    void whenFindAllById() {
        entityManager.persist(firstFlight);
        entityManager.flush();

        //when
        Flight flight = flightRepository.findAllById(firstFlight.getId());

        //then
        assertThat(flight).isEqualTo(firstFlight);
    }
}