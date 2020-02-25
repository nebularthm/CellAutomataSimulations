package cellsociety;

import java.util.List;
//Based on whether or not it is a triangle a square, it'll have a different config, grid knows how to access correct neighbhors for shape, Cell just needs to know what it's neigh hors are
//The rules shouldnt change based on shapes
//does cell even need to know it's neighhors

public class GOLGrid implements  Grid {
    private String shape;
    private int width,height;
    private List<List<GOLCells>> myGrid;
    public GOLGrid(String shap, int wid, int hei){
        shape = shap;
        width = wid;
        height = hei;
    }



    @Override
    public void setShape(String shap) {

    }

    @Override
    public void updateCells() {

    }

    @Override
    public void setUpdateRules() {

    }

    @Override
    public void setGrid(Cell[][] cells) {

    }

    @Override
    public Cell[][] getGrid() {
        return new Cell[0][];
    }


    @Override
    public String getShape() {
        return null;
    }

    @Override
    public void setWidth(int height) {

    }

    @Override
    public void setHeight(int width) {

    }


}
