package cellsociety;

import javafx.scene.control.Cell;

public class GOLCells implements Cell {
    private String state;
    private String [] neighbs;
    private String shape;
    public GOLCells(String stat, String[] nei, String shap){
        state = stat;
        neighbs = nei;
        shape = shap;
    }

    public void setState(String state) {
        this.state = state;
    }
    public void setShape(String shap){
        shape = shap;
    }
    public void setNeighbs(String [] neighb){
        neighbs = neighb;
    }

}
