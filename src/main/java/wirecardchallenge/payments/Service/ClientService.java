package wirecardchallenge.payments.Service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wirecardchallenge.payments.model.Client;
import wirecardchallenge.payments.repository.ClientRepository;

import static wirecardchallenge.WirecardChallengeExceptions.clientNotFoundException;
import static wirecardchallenge.WirecardChallengeExceptions.idRequired;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client findById(Integer id) throws NotFoundException {
        if(id == null)
            throw idRequired;

        return repository.findById(id).orElseThrow(()-> clientNotFoundException);
    }
}
