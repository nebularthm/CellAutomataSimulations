package cellsociety;

import java.util.List;

public class GOLGrid implements  Grid {
    private String shape;
    private int width,height;
    public GOLGrid(String shap, int wid, int hei){
        shape = shap;
        width = wid;
        height = hei;
    }

    @Override
    public void setShape() {

    }

    @Override
    public void updateCells() {

    }

    @Override
    public void setUpdateRules() {

    }

    @Override
    public void setGrid() {

    }

    @Override
    public List getGrid() {
        return null;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
