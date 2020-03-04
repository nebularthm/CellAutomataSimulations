package cellsociety;

import java.util.ArrayList;
import java.util.List;


public abstract class Grid<T> {

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
    private Rules theRules;
    private boolean shouldWrapAround = false;

    public Grid() {
    }


    void setShape(String shap) {
    shape = shap;
    }

    void updateCells() {

    }

    void generateNextStates() {

    }

    void initializeGrid(String[][] initialStates) {
    }

    int getGridHeight() {
    return  gridHeight;

    }

    int getGridWidth() {
        return gridWidth;
    }

    void initializeNeighbhors() {
    }
    void setShouldWrapAround(boolean wrapAround){
        shouldWrapAround = wrapAround;

    }

    Cell[][] getGrid() {
    return myGrid;

    }

    String getShape() {
        return shape;
    }

    boolean validIndex(int x, int y) {
        return (y < gridHeight && x < gridWidth) && (y >=  0 && x >= 0) ;

    }

    String[][] getStringGrid() {
        return new String[0][];
    }

    public abstract List<Cell> eligibleNeighbs(int yPos, int xPos);

    public abstract String getState(int xPos, int yPos);


    //grid should Should I implementation
    //add valid index checking  to Grid

    //Grid takes from the csv, initial configuration, height, width,

    //Grid creates it's own 2d array of cell based on states

    //instead of returning the grid
}
