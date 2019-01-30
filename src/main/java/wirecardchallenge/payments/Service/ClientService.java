package wirecardchallenge.payments.Service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wirecardchallenge.payments.model.Client;
import wirecardchallenge.payments.repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static wirecardchallenge.WirecardChallengeExceptions.*;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {
        client.validateMe();
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Client findById(Integer id) throws NotFoundException, IllegalArgumentException {
        if(id == null)
            throw idRequired;

        return clientRepository.findById(id).orElseThrow(()-> clientNotFound);
    }
}
