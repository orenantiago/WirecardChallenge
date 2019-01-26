package wirecardchallenge.payments.model;

public enum PaymentType {
    CREDITCARD("Credit card"), BOLETO("Boleto");

    public String description;

    PaymentType(String description) {
        this.description = description;
    }
}
