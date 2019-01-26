package wirecardchallenge.payments.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

import static wirecardchallenge.WirecardChallengeExceptions.*;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Min(0)
    private Double amount;

    @NotNull
    private PaymentType type;

    @ManyToOne
    @NotNull
    private Client client;

    @Column(unique = true)
    private Integer cardNumber;

    @Size(min=1,max=50)
    private String cardHolderName;

    @Min(0)
    private Integer cardCvv;

    private Date cardExpiration;

    @NotNull
    @Size(min=1, max=50)
    private String buyerName;

    @NotNull
    @Size(min=1, max=50)
    private String buyerEmail;

    @NotNull
    @Size(min=1, max=20)
    private String buyerCpf;

    @Min(0)
    private Integer boletoNumber;

    public Integer clientId() {
        return client.getId();
    }

    public void validateMe() {
        if (amount  == null || amount < 0)
            throw invalidPaymentAmount;

        if(type == PaymentType.CREDITCARD)
            validateCreditCard();

        if(client == null)
            throw clientRequiredException;

        validateBuyerInfo();

    }

    private void validateBuyerInfo() {
        if(buyerName == null)
            throw invalidBuyerName;
        if(buyerEmail == null)
            throw invalidBuyerEmail;
        if(buyerCpf == null)
            throw invalidBuyerCpf;
    }

    private void validateCreditCard() {
        Date today = new Date();

        if(cardCvv == null || cardCvv < 0)
            throw invalidCardCvv;
        if(cardHolderName == null)
            throw invalidCardHolderName;
        if(cardExpiration == null | cardExpiration.before(today))
            throw invalidCardExpiration;
        if(cardNumber == null || cardNumber < 0)
            throw invalidCardNumber;
    }

    public boolean isBoletoType() {
        return type == PaymentType.BOLETO;
    }
}
