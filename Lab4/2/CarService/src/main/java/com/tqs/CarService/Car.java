package com.tqs.CarService;

import javax.persistence.*;

@Entity
@Table(name = "tqs_car")
public class Car {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long carId;
		private String maker;
		private String model;
		
		public Car() {}
		
		public Car(String s1, String s2) {
			maker = s1;
			model = s2;
		}

		public Long getCarId() {
			return carId;
		}

		public void setCarId(Long carId) {
			this.carId = carId;
		}

		public String getMaker() {
			return maker;
		}

		public void setMaker(String marker) {
			this.maker = marker;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((carId == null) ? 0 : carId.hashCode());
			result = prime * result + ((maker == null) ? 0 : maker.hashCode());
			result = prime * result + ((model == null) ? 0 : model.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Car other = (Car) obj;
			if (carId == null) {
				if (other.carId != null)
					return false;
			} else if (!carId.equals(other.carId))
				return false;
			if (maker == null) {
				if (other.maker != null)
					return false;
			} else if (!maker.equals(other.maker))
				return false;
			if (model == null) {
				if (other.model != null)
					return false;
			} else if (!model.equals(other.model))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Car [carId=" + carId + ", marker=" + maker + ", model=" + model + "]";
		}
		
		
}
