package com.tqs.CarService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindAudi_thenReturnAudi() {
        Car audi = new Car("Audi", "A6");
        entityManager.persistAndFlush(audi); //ensure data is persisted at this point

        Optional<Car> found = carRepository.findByCarId(audi.getCarId());
        assertThat( found.get() ).isEqualTo(audi);
    }

    @Test
    public void whenInvalidCar_returnNull() {
    	Long l = (long) 1000;
    	Optional<Car> found = carRepository.findByCarId(l);
        assertThat( found.isEmpty() );
    }

    @Test
    public void givenListOfCars_whenFindAll_thenListOfCars() {
        Car audi = new Car("audi", "A3");
        Car mercedes= new Car("mercedes", "c220");
        Car bmw = new Car("bmw", "Serie 1");
        Car ibiza  = new Car("seat", "ibiza");

        entityManager.persist(audi);
        entityManager.persist(mercedes);
        entityManager.persist(bmw);
        entityManager.persist(ibiza);
        entityManager.flush();

        List<Car> allCarsFound = carRepository.findAll();

        assertThat(allCarsFound).hasSize(4).extracting(Car::getMaker).containsOnly(audi.getMaker(), mercedes.getMaker(),bmw.getMaker(), ibiza.getMaker());
    }

}