package com.sudoku.validator.exception;

public class SudokuException extends Exception {

    private final ErrorCode errorCode;

    public SudokuException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
