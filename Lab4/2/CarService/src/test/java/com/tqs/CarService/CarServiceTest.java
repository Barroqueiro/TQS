package com.tqs.CarService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.assertj.core.api.Assertions;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;
    
    @Test
    public void whenValidId_ReturnCar() {
    	Car audi = new Car("Audi", "A3");
    	
        
        Long id = (long) 1;
        
        given( carRepository.findByCarId(id)).willReturn(Optional.of(audi));
        
        Optional<Car> sucess = (Optional<Car>) carManagerService.getCarDetails(id);

        Assertions.assertThat(sucess.get().getMaker()).isEqualTo("Audi");
        Assertions.assertThat(sucess.get().getModel()).isEqualTo("A3");
        verify(carRepository, VerificationModeFactory.times(1)).findByCarId(id);
    }
    
    @Test
    public void whenInvalidId_ReturnCar() {
    	Optional op = Optional.empty();
        
        Long id = (long) 1;
        
        given( carRepository.findByCarId(id)).willReturn(op);
        
        Optional<Car> sucess = (Optional<Car>) carManagerService.getCarDetails(id);

        Assertions.assertThat(sucess.isEmpty());
        verify(carRepository, VerificationModeFactory.times(1)).findByCarId(id);
    }
    
    @Test
    public void saveTest() {
    	Car audi = new Car("Audi", "A3");
    	
        
        Long id = (long) 1;
        
        given( carRepository.save(audi)).willReturn(audi);
        
        Car sucess = carManagerService.save(audi);

        Assertions.assertThat(sucess.getMaker()).isEqualTo("Audi");
        Assertions.assertThat(sucess.getModel()).isEqualTo("A3");
        verify(carRepository, VerificationModeFactory.times(1)).save(audi);
    }

    @Test
    public void whenGetAllCars_returnAll() {
        Car audi = new Car("audi", "A3");
        Car mercedes= new Car("mercedes", "c220");
        Car bmw = new Car("bmw", "Serie 1");
        Car ibiza  = new Car("seat", "ibiza");
        
        List<Car> allCars = Arrays.asList(audi,mercedes,bmw,ibiza);
        
        given( carRepository.findAll()).willReturn(allCars);
        
        List<Car> car_list =  carManagerService.getAllCars();

        Assertions.assertThat(car_list).isEqualTo(allCars);
        verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }


}
