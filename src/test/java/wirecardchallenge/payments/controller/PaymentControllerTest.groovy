package wirecardchallenge.payments.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory
import wirecardchallenge.payments.Service.ClientService
import wirecardchallenge.payments.Service.PaymentService
import wirecardchallenge.payments.model.Client
import wirecardchallenge.payments.model.Payment
import wirecardchallenge.payments.model.PaymentType
import wirecardchallenge.payments.repository.ClientRepository
import wirecardchallenge.payments.repository.PaymentRepository

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static groovy.json.JsonOutput.toJson
import static org.springframework.http.MediaType.APPLICATION_JSON



@WebMvcTest(controllers = [PaymentController])
class PaymentControllerTest extends Specification {
    @Autowired
    MockMvc mvc

    @MockBean
    PaymentService service

    def "should pass payment details to domain component and return 'created' status"() {
        given:
        def payment = validPayment()
        service.createPayment(_) >> payment

        when:
        def results = mvc.perform(post('/payments').contentType(APPLICATION_JSON).content(toJson(payment)))

        then:
        results.andExpect(status().isCreated())
    }

    def "should perform get request to specific payment endpoint and return 'ok' status"() {
        given:
        def payment = validPayment()
        service.createPayment(_) >> payment

        when:
        def results = mvc.perform(get('/payments/{id}', 123))

        then:
        results.andExpect(status().isOk())
    }

    def "should perform get request to payments endpoint and return 'ok' status"() {
        given:
        def payment = validPayment()
        service.createPayment(_) >> payment

        when:
        def results = mvc.perform(get('/payments'))

        then:
        results.andExpect(status().isOk())
    }

    private def validPayment() {
        def client = new Client()
        client. name = "test"
        client.cnpj = "74.406.687/0001-04"

        def payment = new Payment()
        payment.amount = 1.00
        payment.type = PaymentType.BOLETO
        payment.client
        payment.buyerName = "test"
        payment.buyerEmail = "email@email.com"
        payment.buyerCpf = "123.123.123-01"

        return payment
    }

    @TestConfiguration
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        PaymentService service() {
            return detachedMockFactory.Stub(PaymentService)
        }

        @Bean
        PaymentRepository repository() {
            return detachedMockFactory.Stub(PaymentRepository)
        }

        @Bean
        ClientService clientService() {
            return detachedMockFactory.Stub(ClientService)
        }

        @Bean
        ClientRepository clientRepository() {
            return detachedMockFactory.Stub(ClientRepository)
        }
    }
}
