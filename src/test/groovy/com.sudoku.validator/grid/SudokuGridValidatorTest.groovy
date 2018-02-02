package com.sudoku.validator.grid

import spock.lang.Specification

class SudokuGridValidatorTest extends Specification {

    SudokuGridValidator validator = new SudokuGridValidator()
    ValidDigitsPredicate predicate = Mock()

    def setup() {
        validator.validDigitsPredicate = predicate
    }

    def "should validate grid when all rows, columns and subgrids are valid"() {
        given:
        SudokuGrid grid = Mock()

        def row = [1]
        def column = [2]
        def subgrid = [3]

        grid.rows >> [row]
        grid.columns >> [column]
        grid.subGrids >> [subgrid]

        predicate.test(row) >> true
        predicate.test(column) >> true
        predicate.test(subgrid) >> true

        when:
        def result = validator.validate(grid)

        then:
        assert result
    }

    def "should NOT validate grid when row is invalid"() {
        given:
        SudokuGrid grid = Mock()

        def row = [1]
        def column = [2]
        def subgrid = [3]

        grid.rows >> [row]
        grid.columns >> [column]
        grid.subGrids >> [subgrid]

        predicate.test(row) >> false
        predicate.test(column) >> true
        predicate.test(subgrid) >> true

        when:
        def result = validator.validate(grid)

        then:
        assert !result
    }

    def "should NOT validate grid when column is invalid"() {
        given:
        SudokuGrid grid = Mock()

        def row = [1]
        def column = [2]
        def subgrid = [3]

        grid.rows >> [row]
        grid.columns >> [column]
        grid.subGrids >> [subgrid]

        predicate.test(row) >> true
        predicate.test(column) >> false
        predicate.test(subgrid) >> true

        when:
        def result = validator.validate(grid)

        then:
        assert !result
    }

    def "should NOT validate grid when subgrid is invalid"() {
        given:
        SudokuGrid grid = Mock()

        def row = [1]
        def column = [2]
        def subgrid = [3]

        grid.rows >> [row]
        grid.columns >> [column]
        grid.subGrids >> [subgrid]

        predicate.test(row) >> true
        predicate.test(column) >> true
        predicate.test(subgrid) >> false

        when:
        def result = validator.validate(grid)

        then:
        assert !result
    }
}
