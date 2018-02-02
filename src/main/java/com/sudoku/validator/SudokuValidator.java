package com.sudoku.validator;

import com.sudoku.validator.exception.ErrorCode;
import com.sudoku.validator.exception.SudokuException;
import com.sudoku.validator.grid.SudokuGrid;
import com.sudoku.validator.grid.SudokuGridParser;
import com.sudoku.validator.grid.SudokuGridValidator;
import com.sudoku.validator.util.FileLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SudokuValidator {

    private SudokuGridParser sudokuGridParser = new SudokuGridParser();
    private SudokuGridValidator sudokuGridValidator = new SudokuGridValidator();
    private FileLoader fileLoader = new FileLoader();

    public ValidationResult validate(File sudokuFile) {
        try {
            List<List<Integer>> rows = sudokuGridParser.parse(fileLoader.loadContent(sudokuFile));

            boolean validationResult = sudokuGridValidator.validate(new SudokuGrid(rows));
            if (validationResult) {
                return new ValidationSuccess();
            }
            return new ValidationError(ErrorCode.INVALID_SOLUTION, "Solution is invalid");
        } catch (SudokuException e) {
            return new ValidationError(e.getErrorCode(), e.getMessage());
        } catch (IOException e) {
            return new ValidationError(ErrorCode.SOLUTION_FILE_LOADING_ERROR, e.getMessage());
        } catch (RuntimeException e) {
            return new ValidationError(ErrorCode.INTERNAL_ERROR, e.getMessage());
        }
    }

    // Visible for tests
    void setSudokuGridParser(SudokuGridParser sudokuGridParser) {
        this.sudokuGridParser = sudokuGridParser;
    }

    // Visible for tests
    void setSudokuGridValidator(SudokuGridValidator sudokuGridValidator) {
        this.sudokuGridValidator = sudokuGridValidator;
    }

    // Visible for tests
    void setFileLoader(FileLoader fileLoader) {
        this.fileLoader = fileLoader;
    }
}
