package cellsociety;

import java.util.List;

public class GOLGrid implements  Grid {
    private String shape;
    private int width,height;
    private GOLCells[][] myGrid;
    public GOLGrid(String shap, int wid, int hei, int[][] initialStates){
        shape = shap;
        width = wid;
        height = hei;
        initializeGrid(initialStates);
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
    public void initializeGrid(int[][] initialStates) {

        myGrid = new GOLCells[height][width];
        for(int yPos = 0; yPos<height; yPos++) {
            for(int xPos = 0; xPos<width; xPos++) {
                int currState = initialStates[xPos][yPos];
                GOLCells currCell = new GOLCells(currState, xPos, yPos);
                myGrid[xPos][yPos] = currCell;
            }
            yPos++;
        }
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
