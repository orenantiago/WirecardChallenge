package wirecardchallenge.payments.Service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wirecardchallenge.payments.model.Client;
import wirecardchallenge.payments.model.Payment;
import wirecardchallenge.payments.repository.PaymentRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static wirecardchallenge.WirecardChallengeExceptions.paymentNotFound;

@Service
public class PaymentService {

    private static final Integer BOLETO_MIN = 100000000;
    private static final Integer BOLETO_MAX = 999999999;


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
    public List<Payment> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void setupPayment(Payment payment) throws NotFoundException {
        Client client = clientService.findById(payment.clientId());
        payment.setClient(client);

        if(payment.isBoletoType())
            payment.setBoletoNumber(generateBoletoNumber());
    }

    private Integer generateBoletoNumber() {
        return ThreadLocalRandom.current().nextInt(BOLETO_MIN, BOLETO_MAX + 1);
    }


}
