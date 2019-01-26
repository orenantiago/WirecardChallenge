package wirecardchallenge.payments.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Shared
import spock.lang.Specification

import wirecardchallenge.payments.model.Client
import wirecardchallenge.payments.model.Payment
import wirecardchallenge.payments.model.PaymentType

@DataJpaTest
class PaymentRepositoryTest extends Specification {

    @Autowired
    ClientRepository clientRepository

    @Autowired
    PaymentRepository repository

    @Shared
    Payment payment

    def "should find payment by id"() {
        given:
        def saved = repository.save(validPayment())

        when:
        def found = repository.findById(saved.id)

        then:
        found.get().id == saved.id
    }

    private def validPayment() {
        def client = new Client()
        client. name = "test"
        client.cnpj = "74.406.687/0001-04"

        def persistedClient = clientRepository.save(client)

        payment = new Payment()
        payment.amount = 1.00
        payment.type = PaymentType.BOLETO
        payment.client = persistedClient
        payment.buyerName = "test"
        payment.buyerEmail = "email@email.com"
        payment.buyerCpf = "123.123.123-01"

        return payment
    }

}
