package com.sudoku.validator;

public class ValidationSuccess implements ValidationResult {
    @Override
    public String printResult() {
        return "VALID";
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
