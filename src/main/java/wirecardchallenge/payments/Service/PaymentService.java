package wirecardchallenge.payments.Service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wirecardchallenge.payments.model.Client;
import wirecardchallenge.payments.model.Payment;
import wirecardchallenge.payments.repository.PaymentRepository;

import static wirecardchallenge.WirecardChallengeExceptions.paymentNotFound;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository repository;

    @Autowired
    ClientService clientService;

    public Payment createPayment(Payment payment) throws NotFoundException {
        payment.validateMe();
        setupPayment(payment);
        return repository.save(payment);
    }

    public Payment findById(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> paymentNotFound);
    }

    public void setupPayment(Payment payment) throws NotFoundException {
        Client client = clientService.findById(payment.clientId());
        payment.setClient(client);
    }

}
