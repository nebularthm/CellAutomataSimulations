package cellsociety;

import java.util.List;

public interface Grid<T> {
    void setShape();
    void updateCells();
    void setUpdateRules();
    void setGrid(List<List<Cell>> cells);
    List<List> getGrid();
    String getShape();
    void setWidth(int height);
    void setHeight(int width);
}
