package br.com.projects.challenge.softexpert.dtos;

import java.math.BigDecimal;

public class PersonDTO {

    private String name;

    private BigDecimal paymentValue;
    private String paymentLink;

    public PersonDTO() {
    }

    public PersonDTO(String name, BigDecimal paymentValue, String paymentLink) {
        this.name = name;
        this.paymentValue = paymentValue;
        this.paymentLink = paymentLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(Double paymentValue) {
        this.paymentValue = new BigDecimal(paymentValue);
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }
}