package cellsociety;

public interface Cell {
    void setState(String state);
    void setNeighbhors();
    void setShape(String shap);
    String[] getNeighbs();
    String getState();
    String getShape();
    void shouldUpdate();
    boolean canUpdate();
}
