package com.tqs.CarService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostCar_createIt( ) throws Exception {
    	Car audi = new Car("Audi", "A1");

        when( service.save(Mockito.any()) ).thenReturn(audi);

        mvc.perform(post("/api/Car").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(audi)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("maker", is("Audi")));

        verify(service, times(1)).save(Mockito.any());

    }

    @Test
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car audi = new Car("audi", "A3");
        Car mercedes= new Car("mercedes", "c220");
        Car bmw = new Car("bmw", "Serie 1");
        Car ibiza  = new Car("seat", "ibiza");
        
        List<Car> allCars = Arrays.asList(audi,mercedes,bmw,ibiza);

        given(service.getAllCars()).willReturn(allCars);

        mvc.perform(get("/api/Car").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(4))).andExpect(jsonPath("$[0].maker", is(audi.getMaker()))).andExpect(jsonPath("$[1].maker", is(mercedes.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(bmw.getMaker())));
        verify(service, VerificationModeFactory.times(1)).getAllCars();

    }
    
    @Test
    public void givenCar_whenGetCarById_thenReturnCar() throws Exception {
    	Car audi = new Car("Audi", "A3");
    
        Long id = (long) 1;

        given(service.getCarDetails(id)).willReturn(Optional.of(audi));

        mvc.perform(get("/api/Car/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("maker", is("Audi")));

        verify(service, VerificationModeFactory.times(1)).getCarDetails(id);

    }
    
    @Test
    public void givenCarIdIvalid_returnNothing() throws Exception {
        Long id = (long) 1;
        Optional op = Optional.empty();

        given(service.getCarDetails(id)).willReturn(op);

        mvc.perform(get("/api/Car/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        verify(service, VerificationModeFactory.times(1)).getCarDetails(id);

    }
    
}