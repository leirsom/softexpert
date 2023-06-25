package br.com.projects.challenge.softexpert.ressources;

import br.com.projects.challenge.softexpert.enuns.PaymentWallet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentResource {

    public String generatePaymentLink(BigDecimal amount, PaymentWallet paymentWallet) {
        return paymentWallet.getUrl() + amount;
    }
}
