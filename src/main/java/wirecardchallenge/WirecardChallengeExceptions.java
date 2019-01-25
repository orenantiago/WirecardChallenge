package wirecardchallenge;

import javassist.NotFoundException;

import java.security.InvalidParameterException;

public class WirecardChallengeExceptions {
//    Client
    public static NotFoundException clientNotFoundException = new NotFoundException("Client not found");

//    Buyer
    public static NotFoundException buyerNotFoundException = new NotFoundException("Buyer not found");


//    Other
    public static IllegalArgumentException idRequired = new IllegalArgumentException("Id required");
    public static IllegalArgumentException cpfRequired = new IllegalArgumentException("CPF required");

}
