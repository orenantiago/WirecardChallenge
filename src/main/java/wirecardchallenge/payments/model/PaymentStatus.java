package wirecardchallenge.payments.model;


public enum PaymentStatus {
    PAID("Paid"), WAITING_PAYMENT("Waiting Payment"), CANCELED("Canceled"),
    REJECTED("Payment Rejected");

    private String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
