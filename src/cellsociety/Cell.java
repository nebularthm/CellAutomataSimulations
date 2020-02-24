package cellsociety;

public interface Cell {
    void setState(String state);
    void setNeighbhors();
    void setShape(String shap);
    String[] getNeighbs();
    String getState();
    void shouldUpdate();
    boolean canUpdate();
}
