package wirecardchallenge.payments.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;


public enum PaymentType implements Serializable {
    CREDIT_CARD("Credit Card"), BOLETO("Boleto");

    private String description;

    PaymentType(String description) {
        this.description = description;
    }

    public String toString() {
        return description;
    }
}
