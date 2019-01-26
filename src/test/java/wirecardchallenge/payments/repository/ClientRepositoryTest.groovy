package wirecardchallenge.payments.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Shared
import spock.lang.Specification
import wirecardchallenge.payments.model.Client

@DataJpaTest
class ClientRepositoryTest extends Specification {

    @Autowired
    ClientRepository repository

    @Shared
    Client client

    def setupSpec() {
        client = new Client()
        client.name = "test"
        client.cnpj = "74.406.687/0001-04"
    }

    def "should find client by id"() {
        given:
        def saved = repository.save(client)

        when:
        def found = repository.findById(saved.id)

        then:
        found.get().id == saved.id
    }

    def "should find all clients"() {
        given:
        repository.save(client)

        when:
        def number = repository.count()

        then:
        number == 1
    }

}
