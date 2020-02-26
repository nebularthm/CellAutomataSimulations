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
    private static final String[] neighbs = {UP, DOWN, LEFT, RIGHT, UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT};
    private static final String ALIVE = "alive";
    private static final String DEAD = "dead";
    private static final String GOL = "Game of Life";
    private static final String RECTANGLE = "Rectangle";


    private String shape;

    private int gridHeight;
    private int gridWidth;
    private Cell[][] myGrid;
    private GOLRules theRules;

    public GOLGrid(String shap, int wid, int hei, String[][] initialStates){
        shape = shap;
        gridHeight = hei;
        gridWidth = wid;
        initializeGrid(initialStates);
        theRules = new GOLRules();
    }



    @Override
    public void setShape(String shap) {
    shape = shap;
    }
    private List<String> neighbsToString(List<Cell> cellList){
        List<String> stateList = new ArrayList<>();
        for(Cell cell:cellList){
            stateList.add(cell.getState());
        }
        return stateList;
    }
    @Override
    public void updateCells() {
        for(int yPos = 0; yPos<gridHeight; yPos++) {
            for(int xPos = 0; xPos<gridWidth; xPos++) {
                if(theRules.shouldUpdateCell(myGrid[yPos][xPos].getState(), neighbsToString(myGrid[yPos][xPos].getNeighbs()))){
                    myGrid[yPos][xPos].shouldUpdate();
                }

            }
        }
    }

    @Override
    public void generateNextStates() {
        for(int yPos = 0; yPos<gridHeight; yPos++) {
            for(int xPos = 0; xPos<gridWidth; xPos++) {
                if(myGrid[yPos][xPos].canUpdate()){
                    myGrid[yPos][xPos].setState(theRules.changeState(myGrid[yPos][xPos].getState()));
                    myGrid[yPos][xPos].shouldUpdate();
                }
            }
        }

    }

    /**
     * initializes the grid based on starting configuration
     * @param initialStates grid of starting configuration
     */
    @Override
    public void initializeGrid(String[][] initialStates) {
        myGrid = new Cell[gridHeight][gridWidth];
        for(int yPos = 0; yPos<gridHeight; yPos++) {
            for(int xPos = 0; xPos<gridWidth; xPos++) {
                String currState = initialStates[yPos][xPos];
                Cell currCell = new Cell(currState, xPos, yPos);
                List<Cell> cellNeighbors = eligibleNeighbs(yPos,xPos);
                currCell.setNeighbhors(cellNeighbors);
                myGrid[yPos][xPos] = currCell;

            }

        }
    }

    @Override
    public void initializeNeighbhors() {

    }


    /**
     * returns the 2d list of cells that represents this grid
     * @return
     */

    @Override
    public Cell[][] getGrid() {
        return myGrid;
    }


    @Override
    public String getShape() {
        return shape;
    }

    @Override
    public void setWidth(int height) {
        gridHeight = height;
    }

    @Override
    public void setHeight(int width) {
        gridWidth = width;
    }

    //I don't think this method works properly for identifying indices within the grid
    @Override

    public boolean validIndex(int x, int y) {
        return (y < gridHeight && x < gridWidth) || (y >= 0 && x >= 0) ;

    }

    /**
     * returns a list of the state of the eligible neigbhors
     * @param yPos
     * @param xPos
     * @return
     */

    public List<Cell> eligibleNeighbs(int yPos, int xPos) {

        List<Cell> cellList = new ArrayList<>();

        for(String nei:neighbs){
            if (nei.equals(UP)) {
                if(validIndex(xPos, yPos-1)){
                    cellList.add(myGrid[yPos-1][xPos]);
                }
            }
            if(nei.equals(DOWN)){
                if(validIndex(xPos, yPos+1)){
                    cellList.add(myGrid[yPos+1][xPos]);
                }

            }
            if(nei.equals(LEFT)){
                if(validIndex(xPos-1, yPos)){
                    cellList.add(myGrid[yPos][xPos-1]);
                }
            }

            if(nei.equals(RIGHT)){
                if(validIndex(xPos+1, yPos)){
                    cellList.add(myGrid[yPos][xPos+1]);
                }
            }
            if(nei.equals(UPRIGHT)){
                if(validIndex(xPos+1, yPos-1)){
                    cellList.add(myGrid[yPos-1][xPos+1]);
                }
            }
            if(nei.equals(UPLEFT)){
                if(validIndex(yPos - 1, xPos-1)){
                    cellList.add(myGrid[yPos-1][xPos-1]);
                }

            }
            if(nei.equals(DOWNRIGHT)){
                if(validIndex(xPos+1, yPos+1)){
                    cellList.add(myGrid[yPos+1][xPos+1]);
                }

            }
            if(nei.equals(DOWNLEFT)){
                if(validIndex(xPos - 1, yPos+1)){
                    cellList.add(myGrid[yPos+1][xPos-1]);
                }

            }

        }
        return cellList;
    }


}
