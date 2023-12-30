package com.team.match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team.match.entity.Car;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
public interface CarRepository extends JpaRepository<Car, Long>, QuerydslPredicateExecutor<Car>{
}
