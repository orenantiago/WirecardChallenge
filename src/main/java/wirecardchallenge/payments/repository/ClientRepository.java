package wirecardchallenge.payments.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wirecardchallenge.payments.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
}
