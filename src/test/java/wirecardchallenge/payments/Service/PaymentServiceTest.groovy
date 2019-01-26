package wirecardchallenge.payments.Service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import wirecardchallenge.WirecardChallengeExceptions
import wirecardchallenge.payments.model.Client
import wirecardchallenge.payments.model.Payment
import wirecardchallenge.payments.model.PaymentType
import wirecardchallenge.payments.repository.ClientRepository
import wirecardchallenge.payments.repository.PaymentRepository

import java.security.InvalidParameterException

@SpringBootTest
class PaymentServiceTest extends Specification {
    @Autowired
    PaymentService service

    @Autowired
    ClientRepository clientRepository

    @Autowired
    PaymentRepository repository

    def "given known payment id should find it"() {
        given:
        def payment = validPayment()
        def id = repository.save(payment).id

        when:
        def found = service.findById(id)

        then:
        found
    }

    def "given valid payment should create it"() {
        given:
        def payment = validPayment()

        when:
        def created = service.createPayment(payment)
        def found = repository.findById(created.id)

        then:
        found
    }

    def "when create payment of type credit card without card info should throw error"() {
        given:
        def payment = validPayment()
        payment.type = PaymentType.CREDITCARD
        payment.cardNumber = 123123


        when:
        service.createPayment(payment)

        then:
        def ex = thrown InvalidParameterException
        ex.message == WirecardChallengeExceptions.invalidCardCvv.message
    }

    def "when create payment without buyer info should throw error"() {
        given:
        def payment = validPayment()
        payment.buyerEmail = null


        when:
        service.createPayment(payment)

        then:
        def ex = thrown InvalidParameterException
        ex.message == WirecardChallengeExceptions.invalidBuyerEmail.message
    }

    def "when create payment with invalid amount should throw error"() {
        given:
        def payment = validPayment()
        payment.amount = -1


        when:
        service.createPayment(payment)

        then:
        def ex = thrown InvalidParameterException
        ex.message == WirecardChallengeExceptions.invalidPaymentAmount.message
    }

    def "when create payment without client should throw error"() {
        given:
        def payment = validPayment()
        payment.client = null

        when:
        service.createPayment(payment)

        then:
        def ex = thrown InvalidParameterException
        ex.message == WirecardChallengeExceptions.clientRequiredException.message
    }

    private def validPayment() {
        def client = new Client()
        client. name = "test"
        client.cnpj = "74.406.687/0001-04"

        def persistedClient = clientRepository.save(client)

        def payment = new Payment()
        payment.amount = 1.00
        payment.type = PaymentType.BOLETO
        payment.client = persistedClient
        payment.buyerName = "test"
        payment.buyerEmail = "email@email.com"
        payment.buyerCpf = "123.123.123-01"

        return payment
    }
}
