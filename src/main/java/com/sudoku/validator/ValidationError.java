package com.sudoku.validator;

import com.sudoku.validator.exception.ErrorCode;

public class ValidationError implements ValidationResult {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public ValidationError(ErrorCode errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String printResult() {
        return String.format("INVALID - %s: %s", errorCode.name(), errorMessage);
    }

    @Override
    public boolean isValid() {
        return false;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
