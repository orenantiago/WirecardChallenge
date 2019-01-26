package wirecardchallenge;

import javassist.NotFoundException;

import java.security.InvalidParameterException;

public class WirecardChallengeExceptions {
    //    Client
    public static NotFoundException clientNotFoundException = new NotFoundException("Client not found");
    public static InvalidParameterException clientRequiredException = new InvalidParameterException("Client required");

    //    Card
    public static InvalidParameterException invalidCardNumber = new InvalidParameterException("Valid card number required");
    public static InvalidParameterException invalidCardHolderName = new InvalidParameterException("Card holder name required");
    public static InvalidParameterException invalidCardCvv = new InvalidParameterException("Valid card cvv number required");
    public static InvalidParameterException invalidCardExpiration = new InvalidParameterException("Valid card expiration date required");

    //    Buyer
    public static InvalidParameterException invalidBuyerName = new InvalidParameterException("Valid buyer name required");
    public static InvalidParameterException invalidBuyerEmail = new InvalidParameterException("Card buyer email address required");
    public static InvalidParameterException invalidBuyerCpf = new InvalidParameterException("Valid buyer cpf number required");

    //    Payment
    public static InvalidParameterException invalidPaymentAmount = new InvalidParameterException("Invalid payment amount");
    public static NotFoundException paymentNotFound = new NotFoundException("Payment not found");

    //    Other
    public static InvalidParameterException idRequired = new InvalidParameterException("Id required");
}
