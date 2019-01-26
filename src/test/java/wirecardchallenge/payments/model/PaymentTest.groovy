package wirecardchallenge.payments.model

import spock.lang.Specification

class PaymentTest extends Specification {
    def "different clients should not be equal"() {
        when:
        def payment1 = new Payment()
        payment1.id = 1
        def payment2 = new Payment()
        payment2.id = 2
        then:
        payment1 != payment2
    }
}
