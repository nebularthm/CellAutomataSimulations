package cellsociety;

import java.util.ArrayList;
import java.util.List;

public abstract class Grid<T> {
    void setShape(String shap) {

    }

    void updateCells() {

    }

    void generateNextStates() {

    }

    void initializeGrid(String[][] initialStates) {
    }

    int getGridHeight() {
    return  0;

    }

    int getGridWidth() {
        return 0;
    }

    public void initializeNeighbhors() {
    }

    Cell[][] getGrid() {
    return new Cell[0][];

    }

    String getShape() {
        return null;
    }

    boolean validIndex(int x, int y) {
        return false;
    }

    String[][] getStringGrid() {
        return new String[0][];
    }

    //grid should Should I implementation
    //add valid index checking  to Grid

    //Grid takes from the csv, initial configuration, height, width,

    //Grid creates it's own 2d array of cell based on states
}
