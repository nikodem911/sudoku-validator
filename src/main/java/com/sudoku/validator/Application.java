package com.sudoku.validator;

import com.sudoku.validator.exception.ErrorCode;

import java.io.File;

public class Application {
    private SudokuValidator sudokuValidator = new SudokuValidator();

    public static void main(String[] args) {
        System.out.println(new Application().run(args));
    }

    public String run(String args[]) {
        ValidationResult result;
        if (args.length != 1) {
            result = new ValidationError(ErrorCode.INVALID_PARAMETERS, "Invalid number of parameters");
        } else {
            result = sudokuValidator.validate(new File(args[0]));
        }
        return result.printResult();
    }
}
