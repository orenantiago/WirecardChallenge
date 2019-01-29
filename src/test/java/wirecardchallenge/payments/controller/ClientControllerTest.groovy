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
import wirecardchallenge.payments.model.Client
import wirecardchallenge.payments.repository.ClientRepository

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static groovy.json.JsonOutput.toJson
import static org.springframework.http.MediaType.APPLICATION_JSON

@WebMvcTest(controllers = [ClientController])
class ClientControllerTest extends Specification {
    @Autowired
    MockMvc mvc

    @MockBean
    ClientService service


    def "should pass payment details to domain component and return 'created' status"() {
        given:
        def client = validClient()
        service.createClient(_) >> client

        when:
        def results = mvc.perform(post('/clients').contentType(APPLICATION_JSON).content(toJson(client)))

        then:
        results.andExpect(status().isCreated())
    }

    def "should perform get request to specific payment endpoint and return 'ok' status"() {
        given:
        def client = validClient()
        service.createClient(_) >> client

        when:
        def results = mvc.perform(get('/clients/{id}', 123))

        then:
        results.andExpect(status().isOk())
    }

    def validClient() {
        def client = new Client()
        client.name = "test"
        client.cnpj = "20.297.028/0001-40"
        return client
    }


    @TestConfiguration
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        ClientService service() {
            return detachedMockFactory.Stub(ClientService)
        }

        @Bean
        ClientRepository clientRepository() {
            return detachedMockFactory.Stub(ClientRepository)
        }
    }
}
