package com.sudoku.validator.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class represents Sudoku grid.
 * It provides methods used by {@link SudokuGridValidator} to validate correctness of the solution.
 */
public class SudokuGrid {

    static final int DEFAULT_GRID_SIZE = 9;

    private final Integer[][] grid;
    private int gridSize;
    private int subgridSize;

    public SudokuGrid(List<List<Integer>> rows) {
        this.gridSize = rows.size();
        // this is used to simplify tests - the testing grids can be smaller than 9x9
        this.subgridSize = (int) Math.sqrt(gridSize);

        this.grid = new Integer[gridSize][];
        IntStream.range(0, gridSize).forEach(i ->
                grid[i] = rows.get(i).toArray(new Integer[gridSize])
        );
    }

    List<List<Integer>> getRows() {
        return IntStream.range(0, gridSize)
                .mapToObj(this::getRowAt)
                .collect(Collectors.toList());
    }

    List<List<Integer>> getColumns() {
        return IntStream.range(0, gridSize)
                .mapToObj(this::getColumnAt)
                .collect(Collectors.toList());
    }

    List<List<Integer>> getSubGrids() {
        List<List<Integer>> subrgrids = new ArrayList<>();

        for (int i = 0; i < gridSize; i += subgridSize) {
            for (int j = 0; j < gridSize; j += subgridSize) {
                subrgrids.add(getSubGridAt(i, j));
            }
        }
        return subrgrids;
    }

    private List<Integer> getRowAt(int index) {
        return IntStream.range(0, gridSize)
                .mapToObj(i -> grid[index][i])
                .collect(Collectors.toList());
    }

    private List<Integer> getColumnAt(int index) {
        return IntStream.range(0, gridSize)
                .mapToObj(i -> grid[i][index])
                .collect(Collectors.toList());
    }

    private List<Integer> getSubGridAt(int x, int y) {
        List<Integer> subgrid = new ArrayList<>(gridSize);
        for (int i = x; i < x + subgridSize; i++) {
            for (int j = y; j < y + subgridSize; j++) {
                subgrid.add(grid[i][j]);
            }
        }
        return subgrid;
    }

}
