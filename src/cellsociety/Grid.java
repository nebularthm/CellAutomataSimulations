package cellsociety;

import java.util.List;

public interface Grid<T> {
    void setShape();
    void updateCells();
    void setUpdateRules();
    void initializeGrid(int[][] initialStates);
    List<List> getGrid();
    String getShape();
    void setWidth(int height);
    void setHeight(int width);
}
