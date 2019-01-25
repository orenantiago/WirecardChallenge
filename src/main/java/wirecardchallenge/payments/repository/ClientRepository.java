package wirecardchallenge.payments.repository;

import org.springframework.data.repository.CrudRepository;
import wirecardchallenge.payments.model.Client;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
