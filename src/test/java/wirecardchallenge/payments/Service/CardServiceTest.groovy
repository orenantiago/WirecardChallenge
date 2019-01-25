package wirecardchallenge.payments.Service

import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import wirecardchallenge.payments.model.Card
import wirecardchallenge.payments.repository.CardRepository

@SpringBootTest
class CardServiceTest extends Specification {
    @Autowired
    CardService service

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

    def "given known number should find card"() {
        given:
        def saved = repository.save(card)

        when:
        def found = service.findCardByNumber(saved.number)

        then:
        found.id == saved.id
    }

    def "given unknown number should not find card by number"() {
        when:
        service.findCardByNumber(123)

        then:
        thrown NotFoundException
    }

    def "when find by number without number should not find card"() {
        when:
        service.findCardByNumber()

        then:
        thrown IllegalArgumentException
    }

}
