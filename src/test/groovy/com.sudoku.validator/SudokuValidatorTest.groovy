package com.sudoku.validator

import com.sudoku.validator.exception.ErrorCode
import com.sudoku.validator.exception.SudokuException
import com.sudoku.validator.grid.SudokuGridParser
import com.sudoku.validator.grid.SudokuGridValidator
import com.sudoku.validator.util.FileLoader
import spock.lang.Specification

class SudokuValidatorTest extends Specification {

    def validator = new SudokuValidator()
    SudokuGridParser sudokuParser = Mock()
    SudokuGridValidator sudokuGridValidator = Mock()
    FileLoader fileLoader = Mock()

    def setup() {
        validator.sudokuGridParser = sudokuParser
        validator.sudokuGridValidator = sudokuGridValidator
        validator.fileLoader = fileLoader
    }

    def "should return validation success"() {
        given:
        File file = Mock()
        List<String> fileContent = Mock()
        fileLoader.loadContent(file) >> fileContent
        sudokuParser.parse(fileContent) >> []
        sudokuGridValidator.validate(_) >> true

        when:
        def result = validator.validate(file)

        then:
        assert result.valid
    }

    def "should return validation error on invalid solution"() {
        given:
        File file = Mock()
        List<String> fileContent = Mock()
        fileLoader.loadContent(file) >> fileContent
        sudokuParser.parse(_) >> []
        sudokuGridValidator.validate(_) >> false

        when:
        def result = validator.validate(file) as ValidationError

        then:
        assert !result.valid
        assert ErrorCode.INVALID_SOLUTION == result.errorCode
    }

    def "should return validation error on IOException"() {
        given:
        File file = Mock()
        def errorMessage = 'Fake IOException'
        fileLoader.loadContent(file) >> { throw new IOException(errorMessage) }

        when:
        def result = validator.validate(file) as ValidationError

        then:
        assert !result.valid
        assert ErrorCode.SOLUTION_FILE_LOADING_ERROR == result.errorCode
        assert errorMessage == result.errorMessage
    }

    def "should return validation error on SudokuException"() {
        given:
        File file = Mock()
        List<String> fileContent = Mock()
        fileLoader.loadContent(file) >> fileContent
        def errorMessage = 'Fake SudokuException'
        def errorCode = ErrorCode.INVALID_FORMAT
        sudokuParser.parse(fileContent) >> { throw new SudokuException(errorCode, errorMessage) }

        when:
        def result = validator.validate(file) as ValidationError

        then:
        assert !result.valid
        assert errorCode == result.errorCode
        assert errorMessage == result.errorMessage
    }

    def "should return validation error on RuntimeException"() {
        given:
        File file = Mock()
        def errorMessage = 'Fake RuntimeException'
        fileLoader.loadContent(file) >> { throw new RuntimeException(errorMessage) }

        when:
        def result = validator.validate(file) as ValidationError

        then:
        assert !result.valid
        assert ErrorCode.INTERNAL_ERROR == result.errorCode
        assert errorMessage == result.errorMessage
    }

}
