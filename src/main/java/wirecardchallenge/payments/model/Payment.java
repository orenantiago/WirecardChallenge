package wirecardchallenge.payments.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wirecardchallenge.Views;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

import static wirecardchallenge.WirecardChallengeExceptions.*;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "payments")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonView(Views.Public.class)
    private Integer id;

    @NotNull
    @Min(0)
    @JsonView(Views.Detail.class)
    private Double amount;

    @NotNull
    @JsonView(Views.Detail.class)
    private PaymentType type;

    @ManyToOne
    @NotNull
    @JsonView(Views.Detail.class)
    private Client client;

    @Column(unique = true)
    @JsonView(Views.Detail.class)
    private Integer cardNumber;

    @Size(min=1,max=50)
    @JsonView(Views.Detail.class)
    private String cardHolderName;

    @Min(0)
    @JsonView(Views.Detail.class)
    private Integer cardCvv;

    @JsonView(Views.Detail.class)
    private Date cardExpiration;

    @NotNull
    @Size(min=1, max=50)
    @JsonView(Views.Detail.class)
    private String buyerName;

    @NotNull
    @Size(min=1, max=50)
    @JsonView(Views.Detail.class)
    private String buyerEmail;

    @NotNull
    @Size(min=1, max=20)
    @JsonView(Views.Detail.class)
    private String buyerCpf;

    @Min(0)
    @JsonView(Views.Public.class)
    private Integer boletoNumber;

    @JsonView(Views.Public.class)
    private PaymentStatus status;

    public Integer clientId() {
        return client.getId();
    }

    public void validateMe() {
        if (amount  == null || amount < 0)
            throw invalidPaymentAmount;

        if(type == PaymentType.CREDIT_CARD)
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
        if(cardExpiration == null || cardExpiration.before(today))
            throw invalidCardExpiration;
        if(cardNumber == null || cardNumber < 0)
            throw invalidCardNumber;
    }

    public boolean isBoletoType() {
        return type == PaymentType.BOLETO;
    }
}
