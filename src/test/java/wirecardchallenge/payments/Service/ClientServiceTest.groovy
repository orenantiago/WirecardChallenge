package wirecardchallenge.payments.Service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.server.ResponseStatusException
import spock.lang.Shared
import spock.lang.Specification
import wirecardchallenge.payments.model.Client
import wirecardchallenge.payments.repository.ClientRepository
import wirecardchallenge.WirecardChallengeExceptions

@SpringBootTest
class ClientServiceTest extends Specification {
    @Autowired
    ClientService service

    @Autowired
    ClientRepository repository

    @Shared
    Client client

    def setupSpec() {
        client = new Client()
        client.name = "test"
        client.cnpj = "74.406.687/0001-04"
    }

    def "given valid client should create it"() {
        when:
        def created = service.createClient(client)
        def found = service.findById(created.id)

        then:
        found.id == created.id
    }

    def "given known id should find client"() {
        given:
        def saved = repository.save(client)

        when:
        def found = service.findById(saved.id)

        then:
        found.id == saved.id
    }

    def "given unknown id should not find client by id"() {
        when:
        service.findById(123)

        then:
        def ex = thrown ResponseStatusException
        ex.message == WirecardChallengeExceptions.clientNotFound.message
    }

    def "when find by id without id should throw error"() {
        given:

        when:
        service.findById()

        then:
        def ex = thrown ResponseStatusException
        ex.message == WirecardChallengeExceptions.idRequired.message
    }

    def "given invalid client should not create it"() {
        when:
        client.name = null
        service.createClient(client)

        then:
        def ex = thrown ResponseStatusException
        ex.message == WirecardChallengeExceptions.invalidClientName.message
    }
}
