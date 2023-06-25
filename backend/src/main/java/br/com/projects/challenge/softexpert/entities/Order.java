package br.com.projects.challenge.softexpert.entities;

import br.com.projects.challenge.softexpert.enuns.PaymentWallet;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Person> people;
    private Double discount;
    private Double surcharge;
    private Double deliveryFee;
    private PaymentWallet paymentWallet;
    private Boolean discountPercent;
    private Boolean surchargePercent;

    public Order() {
        this.people = new ArrayList<>();
    }

    public Order(List<Person> people, Double discount, Double surcharge, Double deliveryFee, PaymentWallet paymentWallet, Boolean discountPercent, Boolean surchargePercent) {
        this.people = people;
        this.discount = discount;
        this.surcharge = surcharge;
        this.deliveryFee = deliveryFee;
        this.paymentWallet = paymentWallet;
        this.discountPercent = discountPercent;
        this.surchargePercent = surchargePercent;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void addPerson(Person person){
        this.people.add(person);
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Double surcharge) {
        this.surcharge = surcharge;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public PaymentWallet getPaymentWallet() {
        return paymentWallet;
    }

    public void setPaymentWallet(PaymentWallet paymentWallet) {
        this.paymentWallet = paymentWallet;
    }

    public Boolean getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Boolean discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Boolean getSurchargePercent() {
        return surchargePercent;
    }

    public void setSurchargePercent(Boolean surchargePercent) {
        this.surchargePercent = surchargePercent;
    }
}
