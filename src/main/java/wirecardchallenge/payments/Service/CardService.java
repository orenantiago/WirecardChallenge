package wirecardchallenge.payments.Service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wirecardchallenge.payments.model.Card;
import wirecardchallenge.payments.repository.CardRepository;

import static wirecardchallenge.WirecardChallengeExceptions.cardNotFoundException;
import static wirecardchallenge.WirecardChallengeExceptions.cardNumberRequired;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;

    public Card findCardByNumber(Integer number) throws NotFoundException {
        if (number == null)
            throw cardNumberRequired;

        return repository.findCardByNumber(number).orElseThrow(() -> cardNotFoundException);
    }
}
