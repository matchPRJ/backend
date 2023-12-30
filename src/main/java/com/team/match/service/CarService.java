package com.team.match.service;

import com.team.match.dto.PageRequestDTO;
import com.team.match.dto.PageResultDTO;
import com.team.match.entity.Car;
import com.team.match.dto.CarDTO;
public interface CarService {

    default Car dtoToEntity(CarDTO dto) {
        Car car = Car.builder()
                .cno(dto.getCno())
                .tag(dto.getTag())
                .type(dto.getType())
                .brand(dto.getBrand())
                .name(dto.getName())
                .price(dto.getPrice())
                .pricer(dto.getPricer())
                .year(dto.getYear())
                .km(dto.getKm())
                .kmr(dto.getKmr())
                .build();
        return car;
    }

    default CarDTO entityToDTO(Car car) {
        CarDTO carDTO = CarDTO.builder()
                .cno(car.getCno())
                .tag(car.getTag())
                .type(car.getType())
                .brand(car.getBrand())
                .name(car.getName())
                .price(car.getPrice())
                .pricer(car.getPricer())
                .year(car.getYear())
                .km(car.getKm())
                .kmr(car.getKmr())
                .build();
        return carDTO;
    }
}
