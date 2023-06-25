package br.com.projects.challenge.softexpert.controllers;

import br.com.projects.challenge.softexpert.dtos.PersonDTO;
import br.com.projects.challenge.softexpert.entities.Order;
import br.com.projects.challenge.softexpert.services.LunchDivisionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class LunchDivisionControllerTest {

    private LunchDivisionController lunchDivisionController;

    @Mock
    private LunchDivisionService lunchDivisionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        lunchDivisionController = new LunchDivisionController();
        lunchDivisionController.lunchDivisionService = lunchDivisionService;
    }

    @Test
    void calculateProportionalDivisionController() {

        // Arrange
        List<PersonDTO> personDTOs = new ArrayList<>();
        personDTOs.add(new PersonDTO("John", BigDecimal.TEN, "https://picpay-payment-wallet.com/payment?amount=10.00"));
        personDTOs.add(new PersonDTO("Jane 2", BigDecimal.valueOf(15.0), "https://picpay-payment-wallet.com/payment?amount=15.00"));

        Order order = new Order();

        Mockito.when(lunchDivisionService.calculateProportionalPerson(order))
                .thenReturn(personDTOs);

        // Act
        ResponseEntity<List<PersonDTO>> response = lunchDivisionController.calculateProportionalDivision(order);

        // Assert
        Assertions.assertEquals(personDTOs, response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }
}
