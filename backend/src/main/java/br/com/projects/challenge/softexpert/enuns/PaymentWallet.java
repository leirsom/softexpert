package br.com.projects.challenge.softexpert.enuns;

public enum PaymentWallet {
    PICPAY("https://picpay-payment-wallet.com/payment?amount=");

    private String url;

    PaymentWallet(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
