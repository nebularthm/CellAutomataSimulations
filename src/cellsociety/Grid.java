package cellsociety;

import java.util.ArrayList;
import java.util.List;

public interface Grid<T> {
    void setShape(String shap);
    void updateCells();
    void generateNextStates();
    void initializeGrid(String[][] initialStates);
    public void initializeNeighbhors();
    Cell[][] getGrid();
    String getShape();
    boolean validIndex(int x, int y);
    String[][] getStringGrid();

    //grid should Should I implementation
    //add valid index checking  to Grid

    //Grid takes from the csv, initial configuration, height, width,

    //Grid creates it's own 2d array of cell based on states
}
