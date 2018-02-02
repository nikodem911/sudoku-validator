package com.sudoku.validator.grid

import spock.lang.Specification

class SudokuGridTest extends Specification {

    def "should return columns"() {
        given:
        SudokuGrid grid = new SudokuGrid([
                [1, 2, 3],
                [4, 5, 6],
                [7, 8, 9],
        ])

        expect:
        assert [[1, 4, 7],
                [2, 5, 8],
                [3, 6, 9]] == grid.columns
    }

    def "should return rows"() {
        given:
        SudokuGrid grid = new SudokuGrid([
                [1, 2, 3],
                [4, 5, 6],
                [7, 8, 9],
        ])

        expect:
        assert [[1, 2, 3],
                [4, 5, 6],
                [7, 8, 9]] == grid.rows
    }

    def "should return subgrids"() {
        given:
        SudokuGrid grid = new SudokuGrid([
                [11, 12, 13, 14],
                [21, 22, 23, 24],
                [31, 32, 33, 34],
                [41, 42, 43, 44],
        ])


        expect:
        assert [[11, 12, 21, 22],
                [13, 14, 23, 24],
                [31, 32, 41, 42],
                [33, 34, 43, 44]] == grid.subGrids
    }
}
