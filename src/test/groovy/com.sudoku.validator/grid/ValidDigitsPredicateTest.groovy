package com.sudoku.validator.grid

import spock.lang.Specification
import spock.lang.Unroll

class ValidDigitsPredicateTest extends Specification {

    ValidDigitsPredicate validator = new ValidDigitsPredicate()

    @Unroll
    def "should consider valid digits #message"() {
        expect:
        result == validator.test(digits)

        where:

        message              | digits                     || result
        'ordered ascending'  | 1..9                       || true
        'ordered descending' | 9..1                       || true
        'unordered'          | [1, 3, 2, 4, 7, 6, 8, 5, 9] | true
    }

    @Unroll
    def "should consider invalid #message"() {
        expect:
        result == validator.test(digits)

        where:

        message                          | digits                            || result
        'digits from invalid range'      | -1..7                             || false
        'too many digits'                | 0..9                              || false
        'too few digits'                 | 1..7                              || false
        'digits with nulls'              | [1, null, 2, 4, 7, 6, null, 5, 9] || false
        'missing digits with duplicates' | [1, 2, 2, 2, 3, 4, 5, 6, 8]       || false
        'all digits with duplicates'     | [1, 2, 3, 4, 5, 6, 7, 8, 9, 9]    || false
    }
}
