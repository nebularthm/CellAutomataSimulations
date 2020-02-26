package cellsociety;

import java.util.ArrayList;
import java.util.List;
//Based on whether or not it is a triangle a square, it'll have a different config, grid knows how to access correct neighbhors for shape, Cell just needs to know what it's neigh hors are
//The rules shouldnt change based on shapes
//does cell even need to know it's neighhors

public class GOLGrid implements  Grid {
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String UPRIGHT = "up right";
    private static final String UPLEFT = "up left";
    private static final String DOWNRIGHT = "down right";
    private static final String DOWNLEFT = "down left";
    private static final String ALIVE = "alive";
    private static final String DEAD = "dead";
    private static final String GOL = "Game of Life";
    private static final String RECTANGLE = "Rectangle";


    private String shape;
    private int gridHeight;
    private int gridWidth;
    public GOLGrid(String shap, int wid, int hei){
        shape = shap;
        gridHeight = wid;
        gridWidth = hei;
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

    @Override
    public boolean validIndex(int i) {
        return (i >= gridHeight && i >= gridWidth) || (i <= gridHeight && i <= gridWidth) ;
    }

    /**
     * returns a list of the state of the eligible neigbhors
     * @param neighbs
     * @param i
     * @param j
     * @return
     */
    @Override
    public List<String> eligibleNeighbs(String[] neighbs, int i, int j) {
        List<String> stateList = new ArrayList<>();
        Cell [][] theGrid = this.getGrid();
        for(String nei:neighbs){
            if (nei.equals(UP)) {
                if(validIndex(j+1)){
                    stateList.add(theGrid[i][j+1].getState());
                }
            }
            if(nei.equals(DOWN)){
                if(validIndex(j-1)){
                    stateList.add(theGrid[i][j-1].getState());
                }

            }
            if(nei.equals(LEFT)){
                if(validIndex(i-1)){
                    stateList.add(theGrid[i-1][j].getState());
                }
            }
            if(nei.equals(RIGHT)){
                if(validIndex(i+1)){
                    stateList.add(theGrid[i+1][j].getState());
                }
            }
            if(nei.equals(UPRIGHT)){
                if(validIndex(i+ 1) && validIndex(j+1)){
                    stateList.add(theGrid[i+1][j+1].getState());
                }
            }
            if(nei.equals(UPLEFT)){
                if(validIndex(i - 1) && validIndex(j + 1)){
                    stateList.add(theGrid[i-1][j+1].getState());
                }

            }
            if(nei.equals(DOWNRIGHT)){
                if(validIndex(i + 1) && validIndex(j - 1)){
                    stateList.add(theGrid[i+1][j-1].getState());
                }

            }
            if(nei.equals(DOWNLEFT)){
                if(validIndex(i - 1) && validIndex(j - 1)){
                    stateList.add(theGrid[i-1][j-1].getState());
                }

            }

        }
        return stateList;
    }


}
