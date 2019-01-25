package wirecardchallenge.payments.model

import spock.lang.Specification

class ClientTest extends Specification {
    def "different clients should not be equal"() {
        when:
        def client1 = new Client()
        def client2 = new Client()

        then:
        client1 != client2
    }
}
