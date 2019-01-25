package wirecardchallenge.payments.repository;

import org.springframework.data.repository.CrudRepository;
import wirecardchallenge.payments.model.Card;

import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, Integer> {
    Optional<Card> findCardByNumber(Integer number);
}
