package wirecardchallenge;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WirecardChallengeExceptions {
    //    Client
    public static ResponseStatusException clientNotFound = new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
    public static ResponseStatusException clientRequiredException = new ResponseStatusException(HttpStatus.NOT_FOUND, "Client required");
    public static ResponseStatusException invalidClientName = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valid client name required");
    public static ResponseStatusException invalidClientCnpj = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valid client cnpj number required");

    //    Card
    public static ResponseStatusException invalidCardNumber = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valid card number required");
    public static ResponseStatusException invalidCardHolderName = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card holder name required");
    public static ResponseStatusException invalidCardCvv = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valid card cvv number required");
    public static ResponseStatusException invalidCardExpiration = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valid card expiration date required");

    //    Buyer
    public static ResponseStatusException invalidBuyerName = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valid buyer name required");
    public static ResponseStatusException invalidBuyerEmail = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card buyer email address required");
    public static ResponseStatusException invalidBuyerCpf = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valid buyer cpf number required");

    //    Payment
    public static ResponseStatusException invalidPaymentAmount = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment amount");
    public static ResponseStatusException paymentNotFound = new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found");

    //    Other
    public static ResponseStatusException idRequired = new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id required");
}
