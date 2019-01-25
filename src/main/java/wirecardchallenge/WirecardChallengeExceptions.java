package wirecardchallenge;

import javassist.NotFoundException;

import java.security.InvalidParameterException;

public class WirecardChallengeExceptions {
//    Client
    public static NotFoundException clientNotFoundException = new NotFoundException("Client not found");

//    Other
    public static InvalidParameterException idRequired = new InvalidParameterException("Id required");
}
