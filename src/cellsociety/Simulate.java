package cellsociety;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author  Michael Williams
 */
public class Simulate {
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
    private int gridHeight;
    private int gridWidth;
    private Grid myGrid;
    private Rules theRules;


    /**
     * the constructor for this simulate
     * @param height
     * @param width
     * @param initial
     * @param type
     */
    public Simulate(int height, int width, String[][] initial, String type){
        gridWidth = width;
        gridHeight = height;
        myGrid = makeGrid(initial,type);

    }

    /**
     * makes the grid given the initial state and the type of game we are playing
     * @param initial
     * @param type
     * @return
     */
    private Grid makeGrid(String[][] initial, String type) {
        Grid retGrid = null;
        if(type.equals(GOL)){
            retGrid = new GOLGrid(RECTANGLE,gridWidth,gridHeight,initial);
        }
        return retGrid;
    }

    ///**This method essentially handles updating the cells in this grid, essentially flips the update switch in one pass then updates elegible cells in the next
     //* @param cellGrid The grid of interest
     //*/
    /*public  void updateCells(){
        Cell[][] theGrid = cellGrid.getGrid();
        for(int i = 0; i < theGrid.length; i++){
            for(int j = 0; j < gridWidth;j++){
            Cell thisCell = theGrid[i][j];
            List<String> validNeighbs =myGrid.eligibleNeighbs(thisCell.getNeighbs(),i,j);
            if(theRules.updateCell(thisCell.getState(),validNeighbs)){
            thisCell.shouldUpdate();
            }

        }
        }

        for(int i = 0; i < theGrid.length;i++){
            for(int j = 0; j <gridWidth;j++){
                Cell thisCell = theGrid[i][j];
                    if(thisCell.canUpdate()){
                        thisCell.setState(theRules.changeState(thisCell.getState()));
                    }
            }
        }

    }
    public Grid getMyGrid() {
        return myGrid;
    }*/

    public void step() {
        myGrid.generateNextStates();
        myGrid.updateCells();
    }
}
