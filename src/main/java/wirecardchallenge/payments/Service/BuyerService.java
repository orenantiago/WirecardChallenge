package wirecardchallenge.payments.Service;

import com.sun.org.apache.regexp.internal.RE;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wirecardchallenge.payments.model.Buyer;
import wirecardchallenge.payments.repository.BuyerRepository;

import static wirecardchallenge.WirecardChallengeExceptions.buyerNotFoundException;
import static wirecardchallenge.WirecardChallengeExceptions.cpfRequired;

@Service
public class BuyerService {
    @Autowired
    BuyerRepository repository;

    public Buyer findBuyerByCpf(String cpf) throws IllegalArgumentException, NotFoundException {
        if (cpf == null)
            throw cpfRequired;

        return repository.findBuyerByCpf(cpf).orElseThrow(() -> buyerNotFoundException);
    }
}
