package wirecardchallenge.payments.repository;

import org.springframework.data.repository.CrudRepository;
import wirecardchallenge.payments.model.Buyer;

import java.util.Optional;

public interface BuyerRepository extends CrudRepository<Buyer, Integer> {
    Optional<Buyer> findBuyerByCpf(String cpf);
}
