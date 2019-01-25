package wirecardchallenge.payments.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Shared
import spock.lang.Specification
import wirecardchallenge.payments.model.Card

@DataJpaTest
class CardRepositoryTest extends Specification {

    @Autowired
    CardRepository repository

    @Shared
    Card card

    def setupSpec() {
        card = new Card()
        card.holderName = "test"
        card.number = 12345678
        card.expiration = new Date()
        card.cvv = 123
    }

    def "should find card by number"() {
        given:
        def saved = repository.save(card)

        when:
        def found = repository.findCardByNumber(saved.number)

        then:
        found.get().number == saved.number
    }

}
