package wirecardchallenge.payments.Service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wirecardchallenge.payments.model.Client;
import wirecardchallenge.payments.model.Payment;
import wirecardchallenge.payments.model.PaymentStatus;
import wirecardchallenge.payments.repository.PaymentRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static wirecardchallenge.WirecardChallengeExceptions.paymentNotFound;
import static wirecardchallenge.payments.model.PaymentStatus.PAID;
import static wirecardchallenge.payments.model.PaymentStatus.REJECTED;

@Service
public class PaymentService {

    private static final Integer BOLETO_MIN = 100000000;
    private static final Integer BOLETO_MAX = 999999999;


    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ClientService clientService;

    public Payment createPayment(Payment payment) throws NotFoundException {
        payment.validateMe();
        processPayment(payment);
        return paymentRepository.save(payment);
    }

    public Payment findById(Integer id) throws NotFoundException {
        return paymentRepository.findById(id).orElseThrow(() -> paymentNotFound);
    }
    public List<Payment> findAll() {
        return StreamSupport.stream(paymentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void processPayment(Payment payment) throws NotFoundException {
        Client client = clientService.findById(payment.clientId());
        payment.setClient(client);

        if(payment.isBoletoType())
            processBoletoPayment(payment);
        else
            processCreditCardPayment(payment);
    }

    private void processCreditCardPayment(Payment payment) {
        if (Math.random() < 0.5) {
            payment.setStatus(PAID);
        } else {
            payment.setStatus(REJECTED);
        }
    }

    private void processBoletoPayment(Payment payment) {
        Integer boletoNumber =  ThreadLocalRandom.current().nextInt(BOLETO_MIN, BOLETO_MAX + 1);
        payment.setBoletoNumber(boletoNumber);
        payment.setStatus(PaymentStatus.WAITING_PAYMENT);
    }

}
