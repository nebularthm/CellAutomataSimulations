package cellsociety;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author  Michael Williams
 */
public class Simulate {

    private static final String GOL = "Game of Life";
    private static final String RECTANGLE = "Rectangle";
    private int gridHeight;
    private int gridWidth;
    private Grid myGrid;

    /**
     * the constructor for this simulate
     *
     * @param height
     * @param width
     * @param initial
     * @param type
     */
    public Simulate(int height, int width, String[][] initial, String type) {
        gridWidth = width;
        gridHeight = height;
        myGrid = makeGrid(initial, type);

    }

    /**
     * makes the grid given the initial state and the type of game we are playing
     *
     * @param initial
     * @param type
     * @return
     */
    private Grid makeGrid(String[][] initial, String type) {
        Grid retGrid = null;
        if (type.equals(GOL)) {
            retGrid = new GOLGrid(RECTANGLE, gridWidth, gridHeight, initial);
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


    public void step() {
        myGrid.updateCells();
        myGrid.generateNextStates();
    }

    /**
     * this method returns 2d grid of cells to the controller
     * @return
     */
    public String[][] getStringGrid() {
        Cell[][] gridBeforeConv = myGrid.getGrid();
        String[][] stringGrid = new String[gridWidth][gridHeight];
        for (int i = 0; i < gridBeforeConv.length; i++) {
            for (int j = 0; j < gridBeforeConv[0].length; j++) {
                stringGrid[i][j] = gridBeforeConv[i][j].getState();
            }
        }

        return stringGrid;


    }
}
