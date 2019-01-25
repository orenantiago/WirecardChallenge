package wirecardchallenge.payments.repository;

import org.springframework.data.repository.CrudRepository;
import wirecardchallenge.payments.model.Buyer;

public interface BuyerRepository extends CrudRepository<Buyer, Integer> {
}
