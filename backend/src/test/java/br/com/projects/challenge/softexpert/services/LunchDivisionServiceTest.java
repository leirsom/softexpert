package br.com.projects.challenge.softexpert.services;

import br.com.projects.challenge.softexpert.dtos.PersonDTO;
import br.com.projects.challenge.softexpert.entities.Item;
import br.com.projects.challenge.softexpert.entities.Order;
import br.com.projects.challenge.softexpert.entities.Person;
import br.com.projects.challenge.softexpert.enuns.PaymentWallet;
import br.com.projects.challenge.softexpert.exceptions.NumberExceptions;
import br.com.projects.challenge.softexpert.ressources.PaymentResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

class LunchDivisionServiceTest {

    private LunchDivisionService lunchDivisionService;

    @Mock
    private PaymentResource paymentResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        lunchDivisionService = new LunchDivisionService();
        lunchDivisionService.paymentResource = paymentResource;
    }

    @Test
    void calculateProportionalDivision() {
        // Arrange
        Person person1 = new Person();
        person1.setName("John");
        person1.addItem(new Item("Item 1", 40.0));
        person1.addItem(new Item("Item 2", 2.0));

        Person person2 = new Person();
        person2.setName("Jane");
        person2.addItem(new Item("Item 3", 8.0));

        Order order = new Order();
        order.addPerson(person1);
        order.addPerson(person2);
        order.setDiscount(20.0);
        order.setSurcharge(0.0);
        order.setDeliveryFee(8.0);
        order.setDiscountPercent(false);
        order.setSurchargePercent(false);
        order.setPaymentWallet(PaymentWallet.PICPAY);

        Mockito.when(paymentResource.generatePaymentLink(Mockito.any(BigDecimal.class), Mockito.any(PaymentWallet.class)))
                .thenCallRealMethod();

        // Act
        List<PersonDTO> result = lunchDivisionService.calculateProportionalPerson(order);

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("John", result.get(0).getName());
        Assertions.assertEquals(new BigDecimal("31.92"), result.get(0).getPaymentValue());
        Assertions.assertEquals("https://picpay-payment-wallet.com/payment?amount=31.92", result.get(0).getPaymentLink());
        Assertions.assertEquals("Jane", result.get(1).getName());
        Assertions.assertEquals(new BigDecimal("6.08"), result.get(1).getPaymentValue());
        Assertions.assertEquals("https://picpay-payment-wallet.com/payment?amount=6.08", result.get(1).getPaymentLink());
    }

    @Test
    void calculateProportionalDivisionWithNumberExceptions() {
        // Arrange
        Person person1 = new Person();
        person1.setName("John");
        person1.addItem(new Item("Item 1", 0.0));
        person1.addItem(new Item("Item 2", 0.0));

        Person person2 = new Person();
        person2.setName("Jane");
        person2.addItem(new Item("Item 3", 0.0));

        Order order = new Order();
        order.addPerson(person1);
        order.addPerson(person2);
        order.setDiscount(20.0);
        order.setSurcharge(0.0);
        order.setDeliveryFee(8.0);
        order.setDiscountPercent(false);
        order.setSurchargePercent(false);
        order.setPaymentWallet(PaymentWallet.PICPAY);

        // Act & Assert
        Assertions.assertThrows(NumberExceptions.class, () -> lunchDivisionService.calculateProportionalPerson(order));
    }
}