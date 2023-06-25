package br.com.projects.challenge.softexpert.services;

import br.com.projects.challenge.softexpert.dtos.PersonDTO;
import br.com.projects.challenge.softexpert.entities.Item;
import br.com.projects.challenge.softexpert.entities.Order;
import br.com.projects.challenge.softexpert.entities.Person;
import br.com.projects.challenge.softexpert.exceptions.NumberExceptions;
import br.com.projects.challenge.softexpert.ressources.PaymentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class LunchDivisionService {

    @Autowired
    PaymentResource paymentResource;

    public List<PersonDTO> calculateProportionalPerson(Order order) {
        List<PersonDTO> personDTOS = new ArrayList<>();
        Double totalValueItemsOrder = calculateValueItemsOrder(order);
        Double discount = calculateDiscount(order, totalValueItemsOrder);
        Double surcharge = calculateSurcharge(order, totalValueItemsOrder);
        Double deliveryFee = order.getDeliveryFee();

        try {
            for (Person person : order.getPeople()) {
                BigDecimal proportionalValue = BigDecimal.valueOf((totalValueItemsOrder + surcharge + deliveryFee - discount) * (calculateValueItems(person) / totalValueItemsOrder));
                proportionalValue = proportionalValue.setScale(2, RoundingMode.HALF_UP);
                String paymentURL = paymentResource.generatePaymentLink(proportionalValue, order.getPaymentWallet());
                personDTOS.add(new PersonDTO(person.getName(), proportionalValue, paymentURL));
            }
        } catch (NumberFormatException e){
            throw new NumberExceptions("Itens não podem conter valor zero!");
        }

        return personDTOS;
    }


    private Double calculateValueItemsOrder(Order order){
        return order.getPeople().stream()
                .flatMap(person -> person.getItems().stream())
                .mapToDouble(Item::getPrice).sum();
    }

    private Double calculateValueItems(Person person) {
        return person.getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum();
    }

    private Double calculateDiscount(Order order, Double totalOrder) {
        if (order.getDiscountPercent()) {
            return totalOrder * (order.getDiscount() / 100);
        }
        return order.getDiscount();
    }

    private Double calculateSurcharge(Order order, Double totalOrder) {
        if (order.getSurchargePercent()) {
            return totalOrder * (order.getSurcharge() / 100);
        }
        return order.getSurcharge();
    }
}