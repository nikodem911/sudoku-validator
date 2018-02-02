package com.sudoku.validator.grid

import com.sudoku.validator.exception.SudokuException
import spock.lang.Specification
import spock.lang.Unroll

import static com.sudoku.validator.exception.ErrorCode.INVALID_FORMAT

class SudokuGridParserTest extends Specification {

    SudokuGridParser parser = new SudokuGridParser(3)

    @Unroll
    def "should parse #message"() {

        expect:
        expected == parser.parse(lines)

        where:
        message                        | lines                                        || expected
        'valid lines'                  | ['1,2,3', '4,5,6', '7,8,9']                  || [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
        'valid lines with whitespaces' | ['1, 2, 3', '4, 5,  6  ', '  7, 8, 9']       || [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
        'valid lines with empty lines' | ['', '1, 2, 3', '4,5,6', ' ', '7,8,9', '  '] || [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
    }

    @Unroll
    def "should throw exception when #message"() {
        when:
        def ignore = parser.parse(lines)

        then:
        SudokuException exception = thrown()
        error == exception.errorCode

        where:
        message                            | lines                                || error
        'line is too long'                 | ['1,2,3,4']                          || INVALID_FORMAT
        'line is too short'                | ['1,2']                              || INVALID_FORMAT
        'line contains empty characters'   | ['1,']                               || INVALID_FORMAT
        'line contains invalid characters' | ['1,a']                              || INVALID_FORMAT
        'line uses invalid separator'      | ['1.2.3']                            || INVALID_FORMAT
        'line contains double quotes'      | ['"1", "2", "3"']                    || INVALID_FORMAT
        'too few lines'                    | ['1,2,3', '4,5,6']                   || INVALID_FORMAT
        'too many lines'                   | ['1,2,3', '4,5,6', '7,8,9', '1,2,3'] || INVALID_FORMAT

    }

}
