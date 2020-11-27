package com.cloud.item.repository;

import com.cloud.item.model.Arrival;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
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
class ArrivalRepositoryTest {

    @Autowired
    private ArrivalRepository arrivalRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindAll() {
        Arrival firstArrival = new Arrival();
        firstArrival.setCity("Meerut");
        arrivalRepository.save(firstArrival);

        Arrival secondArrival = new Arrival();
        secondArrival.setCity("Meerut");
        arrivalRepository.save(secondArrival);

        List<Arrival> arrivalList = arrivalRepository.findAll();

        assertEquals(2, arrivalList.size());
        assertEquals(arrivalList.get(0), firstArrival);
        assertEquals(arrivalList.get(1), secondArrival);
    }

    @Test
    public void whenFindAllById() {
        //given
        Arrival arrival = new Arrival();
        arrival.setCity("Yerevan");
        entityManager.persist(arrival);
        entityManager.flush();

        //when
        Arrival testArrival = arrivalRepository.findAllById(arrival.getId());

        //then
        assertThat(testArrival.getCity()).isEqualTo(arrival.getCity());
    }
}