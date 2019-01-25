package wirecardchallenge.payments.Service

import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import wirecardchallenge.payments.model.Buyer
import wirecardchallenge.payments.model.Client
import wirecardchallenge.payments.repository.BuyerRepository
import wirecardchallenge.payments.repository.ClientRepository

@SpringBootTest
class BuyerServiceTest extends Specification {
    @Autowired
    BuyerService service

    @Autowired
    BuyerRepository repository

    @Shared
    Buyer buyer

    def setupSpec() {
        buyer = new Buyer()
        buyer.name = "test"
        buyer.email = "abc@email"
        buyer.cpf = "123.123.123-02"
    }

    def "given known cpf should find buyer"() {
        given:
        def saved = repository.save(buyer)

        when:
        def found = service.findBuyerByCpf(buyer.cpf)

        then:
        found.id == saved.id
    }

    def "given unknown cpf should not find buyer"() {
        when:
        service.findBuyerByCpf("123")

        then:
        thrown NotFoundException
    }

    def "when find by cpf without cpf should not find buyer"() {
        when:
        service.findBuyerByCpf()

        then:
        thrown IllegalArgumentException
    }
}
