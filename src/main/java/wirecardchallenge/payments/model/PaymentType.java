package wirecardchallenge.payments.model;

public enum PaymentType {
    CREDIT_CARD("Credit Card"), BOLETO("Boleto");

    private String description;

    PaymentType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
