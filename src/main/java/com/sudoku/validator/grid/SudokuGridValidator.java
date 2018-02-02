package com.sudoku.validator.grid;

import java.util.stream.Stream;

/**
 * Class is responsible for validating the correctness of {@link SudokuGrid}.
 * It uses {@link ValidDigitsPredicate} to validate each row, column or subgrid.
 */
public class SudokuGridValidator {

    private ValidDigitsPredicate validDigitsPredicate = new ValidDigitsPredicate();

    public boolean validate(SudokuGrid grid) {
        return Stream.of(grid.getRows().stream(), grid.getColumns().stream(), grid.getSubGrids().stream())
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .allMatch(validDigitsPredicate);
    }

    // visible for tests
    void setValidDigitsPredicate(ValidDigitsPredicate validDigitsPredicate) {
        this.validDigitsPredicate = validDigitsPredicate;
    }
}
