package wirecardchallenge.payments.model

import spock.lang.Specification

class BuyerTest extends Specification {
    def "different buyers should not be equal"() {
        when:
        def b1 = new Buyer()
        def b2 = new Buyer()

        then:
        b1 != b2
    }
}
