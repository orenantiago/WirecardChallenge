package wirecardchallenge.payments.model

import spock.lang.Specification

class ClientTest extends Specification {
    def "different clients should not be equal"() {
        when:
        def client1 = new Client()
        client1.id = 1
        def client2 = new Client()
        client1.id = 2

        then:
        client1 != client2
    }
}
