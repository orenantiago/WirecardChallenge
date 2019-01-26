package wirecardchallenge.payments.repository;

import org.springframework.data.repository.CrudRepository;
import wirecardchallenge.payments.model.Payment;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {

    List<Payment> findPaymentByClientId(Integer clientId);
}
