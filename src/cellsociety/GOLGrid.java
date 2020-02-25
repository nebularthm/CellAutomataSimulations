package cellsociety;

import java.util.List;

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
    public void setShape() {

    }

    @Override
    public void updateCells() {

    }

    @Override
    public void setUpdateRules() {

    }

    @Override
    public void setGrid(List cells) {

    }



    @Override
    public List<List> getGrid() {
        return null;
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
