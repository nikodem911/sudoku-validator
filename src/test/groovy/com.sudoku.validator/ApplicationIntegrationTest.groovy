package com.sudoku.validator

import spock.lang.Specification

class ApplicationIntegrationTest extends Specification {

    Application application = new Application()

    def "should print VALID for valid solution"() {
        given:
        String input = this.getClass().getResource('/sudoku_valid.txt').file

        when:
        def result = application.run([input] as String[])

        then:
        assert 'VALID' == result
    }

    def "should print message starting with INVALID for invalid solution"() {
        given:
        String input = this.getClass().getResource('/sudoku_invalid.txt').file

        when:
        def result = application.run([input] as String[])

        then:
        assert result =~ 'INVALID'
    }

    def "should print message starting with INVALID when argument list is too short"() {
        when:
        def result = application.run([] as String[])

        then:
        assert result =~ 'INVALID'
    }

    def "should print message starting with INVALID when argument list is too long"() {
        when:
        def result = application.run(['PARAM1', 'PARAM2'] as String[])

        then:
        assert result =~ 'INVALID'
    }
}
