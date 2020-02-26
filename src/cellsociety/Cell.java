package cellsociety;

import java.util.List;

public interface Cell {
    void setState(String state);
    void setNeighbhors(List<Cell> neighbhors);
    void setShape(String shap);
    String[] getNeighbs();
    String getState();
    void shouldUpdate();
    boolean canUpdate();
}
