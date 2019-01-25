package wirecardchallenge.payments.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Shared
import spock.lang.Specification
import wirecardchallenge.payments.model.Buyer

@DataJpaTest
class BuyerRepositoryTest extends Specification {

    @Autowired
    BuyerRepository repository

    @Shared
    Buyer buyer

    def setupSpec() {
        buyer = new Buyer()
        buyer.name = "test"
        buyer.email = "abc@abc.com"
        buyer.cpf = "123.123.123-12"
    }

    def "should find buyer by id"() {
        given:
        def saved = repository.save(buyer)

        when:
        def found = repository.findById(saved.id)

        then:
        found.get().id == saved.id
    }

    def "should find all buyer"() {
        given:
        repository.save(buyer)

        when:
        def number = repository.count()

        then:
        number == 1
    }
}
