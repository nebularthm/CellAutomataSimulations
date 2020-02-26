package cellsociety;

import java.util.ArrayList;
import java.util.List;

public interface Grid<T> {
    void setShape(String shap);
    void updateCells();
    void generateNextStates();
    void initializeGrid(String[][] initialStates);
    Cell[][] getGrid();
    String getShape();
    void setWidth(int height);
    void setHeight(int width);
    boolean validIndex(int i);

    List<String> eligibleNeighbs(String[] neighbs,int i, int j);
    //grid should Should I implementation
    //add valid index checking  to Grid

    //Grid takes from the csv, initial configuration, height, width,

    //Grid creates it's own 2d array of cell based on states
}
