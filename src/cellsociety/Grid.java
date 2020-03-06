package cellsociety;

import java.util.ArrayList;
import java.util.List;


public abstract class Grid<T> {


    private static final String ALIVE = "alive";
    private static final String DEAD = "dead";
    private static final String GOL = "Game of Life";
    private static final String RECTANGLE = "Rectangle";


    private String shape;

    protected int gridHeight;
    protected int gridWidth;
    protected Cell[][] myGrid;
    protected Rules theRules;
    protected boolean shouldWrapAround = false;
    protected  String[] neighbs;

    public Grid(String shap, int wid, int hei, String[][] initialStates, Rules rules){
        shape = shap;
        gridHeight = hei;
        gridWidth = wid;
        initializeGrid(initialStates);
        theRules = rules;
        neighbs = theRules.possibleNeighbs();

    }


    void setShape(String shap) {
    shape = shap;
    }

    void updateCells() {


    }
    void changeStateSingleCell(int xPos, int yPos){

    }

    abstract void generateNextStates();

    void   initializeGrid(String[][] initialStates){
        myGrid = new Cell[gridHeight][gridWidth];
        for(int yPos = 0; yPos<gridHeight; yPos++) {
            for(int xPos = 0; xPos<gridWidth; xPos++) {
                String currState = initialStates[yPos][xPos];
                Cell currCell = new Cell(currState, xPos, yPos);
                myGrid[yPos][xPos] = currCell;

            }

        }
    }

    int getGridHeight() {
    return  gridHeight;

    }

    int getGridWidth() {
        return gridWidth;
    }

     void initializeNeighbhors(){

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

    public abstract List<String> getUnmodifiablePossibleStates() ;



    //grid should Should I implementation
    //add valid index checking  to Grid

    //Grid takes from the csv, initial configuration, height, width,

    //Grid creates it's own 2d array of cell based on states

    //instead of returning the grid
}
