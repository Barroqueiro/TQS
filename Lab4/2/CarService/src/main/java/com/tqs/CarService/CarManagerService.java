package com.tqs.CarService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagerService {

		@Autowired
		private CarRepository carRepository;
		
		public Car save(Car c) {
			return carRepository.save(c);
		}
		
		public List<Car> getAllCars(){
			return carRepository.findAll();
		}
		
		public Optional<Car> getCarDetails(Long l){
			return carRepository.findByCarId(l);
		}
}
