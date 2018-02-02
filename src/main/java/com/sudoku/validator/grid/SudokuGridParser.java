package com.sudoku.validator.grid;

import com.sudoku.validator.exception.ErrorCode;
import com.sudoku.validator.exception.SudokuException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.sudoku.validator.grid.SudokuGrid.DEFAULT_GRID_SIZE;

/**
 * Class is responsible for parsing input lines into the form acceptable by {@link SudokuGrid} class.
 * It accepts CSV file format (but it doesn't support double quotes around digits).
 */
public class SudokuGridParser {
    private final static String SEPARATOR = ",";
    private final int gridSize;

    public SudokuGridParser() {
        this(DEFAULT_GRID_SIZE);
    }

    // visible for tests
    SudokuGridParser(int gridSize) {
        this.gridSize = gridSize;
    }

    // List of string is passed for readability and testability reasons.
    // If performance was important it would be redesigned to abort reading the file
    // in case of parsing error or exceeding number of expected lines
    public List<List<Integer>> parse(List<String> lines) throws SudokuException {
        try {
            List<List<Integer>> rows = lines.stream()
                    // The parser could use REGEXP to find only lines matching pattern.
                    // In that case there would be just one error for all violation of the expected pattern.
                    // Current approach can return more detailed errors.
                    .filter(line -> !line.trim().isEmpty())
                    .map(line -> {
                        List<Integer> row = Arrays.stream(line.split(SEPARATOR))
                                .map(String::trim)
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());
                        if (row.size() != gridSize) {
                            throw new RuntimeException("Invalid number of digits in row");
                        }
                        return row;
                    })
                    .collect(Collectors.toList());
            if (rows.size() != gridSize) {
                throw new SudokuException(ErrorCode.INVALID_FORMAT, "Invalid number of rows");
            }
            return rows;
        } catch (RuntimeException e) {
            throw new SudokuException(ErrorCode.INVALID_FORMAT, e.getMessage());
        }
    }
}
