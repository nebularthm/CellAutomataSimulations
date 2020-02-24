package cellsociety;

import java.util.List;

public interface Grid<T> {
    void setShape();
    void updateCells();
    void setUpdateRules();
    void setGrid();
    List getGrid();

}
