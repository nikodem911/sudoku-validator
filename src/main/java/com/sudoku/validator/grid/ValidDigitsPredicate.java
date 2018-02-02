package com.sudoku.validator.grid;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import static com.sudoku.validator.grid.SudokuGrid.DEFAULT_GRID_SIZE;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

/**
 * Class validates correctness of list of digits representing row, column or subgrid.
 */
public class ValidDigitsPredicate implements Predicate<List<Integer>> {
    private static final Collection<Integer> VALID_DIGITS = range(1, DEFAULT_GRID_SIZE + 1)
            .boxed()
            .collect(toSet());

    @Override
    public boolean test(List<Integer> digits) {
        return digits.size() == VALID_DIGITS.size() && digits.containsAll(VALID_DIGITS);
    }
}
