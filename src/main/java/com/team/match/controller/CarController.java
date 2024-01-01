package com.team.match.controller;


import com.team.match.dto.CarDTO;
import com.team.match.dto.PageRequestDTO;
import com.team.match.dto.PageResultDTO;
import com.team.match.entity.Car;
import com.team.match.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/car")
@Log4j2
@RequiredArgsConstructor
public class CarController {

    @Autowired
    private final CarService service;

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<CarDTO, Car>> list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list"+pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));

        return new ResponseEntity<>(service.getList(pageRequestDTO), HttpStatus.OK);
    }
}
