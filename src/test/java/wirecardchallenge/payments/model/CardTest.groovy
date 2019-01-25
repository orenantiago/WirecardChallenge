package wirecardchallenge.payments.model

import spock.lang.Specification

class CardTest extends Specification {
    def "different buyers should not be equal"() {
        when:
        def card1 = new Card()
        def card2 = new Card()

        then:
        card1 != card2
    }
}
