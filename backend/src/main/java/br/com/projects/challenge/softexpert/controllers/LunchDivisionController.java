package br.com.projects.challenge.softexpert.controllers;

import br.com.projects.challenge.softexpert.dtos.PersonDTO;
import br.com.projects.challenge.softexpert.entities.Order;
import br.com.projects.challenge.softexpert.services.LunchDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class LunchDivisionController {

    @Autowired
    LunchDivisionService lunchDivisionService;

    @PostMapping("/calculate")
    public ResponseEntity<List<PersonDTO>> calculateProportionalDivision(@RequestBody Order order){
        List<PersonDTO> proportionalDivision = lunchDivisionService.calculateProportionalPerson(order);
        return ResponseEntity.ok().body(proportionalDivision);
    }
}
