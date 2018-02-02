package com.sudoku.validator.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class is responsible for reading the content of the file with solution.
 */
// The logic was extracted to separate class to separate the concerns and make testing easier.
public class FileLoader {

    public List<String> loadContent(File file) throws IOException {
        return Files.readAllLines(Paths.get(file.toURI()));
    }
}
