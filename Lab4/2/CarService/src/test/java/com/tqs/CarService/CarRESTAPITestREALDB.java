package com.tqs.CarService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

// switch AutoConfigureTestDatabase with TestPropertySource to use a real database
//@TestPropertySource( locations = "application-integrationtest.properties")

@TestPropertySource( locations = "application-integrationtest.properties")
public class CarRESTAPITestREALDB {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
    public void testCreate() {
        Car audi = new Car("Audi", "A3");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/Car", audi, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("Audi");
    }
    
    @Test
    public void testGetById() {
        Car audi = new Car("Audi", "A3");
        Long l = (long) 1;
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/Car", audi, Car.class);

        Optional<Car> found = repository.findByCarId(l);
        assertThat(found.get()).extracting(Car::getMaker).isEqualTo("Audi");
    }

    @Test
    public void givenListOfCars_whenGetCars_thenStatus200()  {
        Car audi = new Car("audi", "A3");
        Car mercedes= new Car("mercedes", "c220");
        Car bmw = new Car("bmw", "Serie 1");
        Car ibiza  = new Car("seat", "ibiza");

        ResponseEntity<Car> entity1 = restTemplate.postForEntity("/api/Car", audi, Car.class);
        ResponseEntity<Car> entity2 = restTemplate.postForEntity("/api/Car", mercedes, Car.class);
        ResponseEntity<Car> entity3 = restTemplate.postForEntity("/api/Car", bmw, Car.class);
        ResponseEntity<Car> entity4 = restTemplate.postForEntity("/api/Car", ibiza, Car.class);
        
        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/Car", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(4).extracting(Car::getMaker).containsOnly(audi.getMaker(), mercedes.getMaker(),bmw.getMaker(), ibiza.getMaker());

    }

}

