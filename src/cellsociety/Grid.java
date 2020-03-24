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


    /**
     * constructs a grid object based on the below parameterss, a grid holds all cells in the simulation
     * @param shap shape of the grid
     * @param wid width of the grid
     * @param hei height of the gri
     * @param initialStates initial states/config of the grid
     * @param rules the rules for the simulation of interest
     */
    public Grid(String shap, int wid, int hei, String[][] initialStates, Rules rules){
        shape = shap;
        gridHeight = hei;
        gridWidth = wid;
        initializeGrid(initialStates);
        theRules = rules;
        neighbs = theRules.possibleNeighbs();

    }

    /**
     *
     * @param shap the shape that this grid will be set to
     */
    void setShape(String shap) {
    shape = shap;
    }

    /**
     * updates the cells in the grid
     */
    void updateCells() {


    }

    /**
     * changes single cell
     * @param yPos y pos of a cell
     * @param xPos x pos of a cell
     */
    void changeStateSingleCell(int xPos, int yPos){

    }

    /**
     * generates the next states for the updatable cells
     */
    abstract void generateNextStates();

    /**
     * initializes the grid based on an initial configuration of states
     * @param initialStates string grid of initialization of states
     */
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

    /**
     *
     * @return height of the grid
     */
    int getGridHeight() {
    return  gridHeight;

    }

    /**
     *
     * @return width of the grid
     */
    int getGridWidth() {
        return gridWidth;
    }

    /**
     * initialez each cell with its valid neighbors
     */
     void initializeNeighbhors(){

     }

    /**
     *
     * @param wrapAround for grids where neighbs wrap around, sets grid neighb behavior to wrap around
     */
    void setShouldWrapAround(boolean wrapAround){
        shouldWrapAround = wrapAround;

    }

    /**
     *
     * @return returns a 2d array of cells
     */

    Cell[][] getGrid() {
    return myGrid;

    }

    /**
     *
     * @return shape of grid
     */
    String getShape() {
        return shape;
    }

    /**
     *
     * @param x x coordinate
     * @param y y coordinnate of cell
     * @return whether that index is valid
     */
    boolean validIndex(int x, int y) {
        return (y < gridHeight && x < gridWidth) && (y >=  0 && x >= 0) ;

    }

    /**
     *
     * @return get string rep of states of cells of grid
     */
    String[][] getStringGrid() {
        return new String[0][];
    }

    /**
     * given coords of cell
     * @param yPos
     * @param xPos
     * @return the list of neighbs of the cell
     */
    public abstract List<Cell> eligibleNeighbs(int yPos, int xPos);

    /**
     * based on below params
     * @param xPos x coordinate
     * @param yPos y coordinate of cell
     * @return state of cell
     */
    public abstract String getState(int xPos, int yPos);

    /**
     *
     * @return returns an nmodifiable list of possible states for a given simulation
     */
    public abstract List<String> getUnmodifiablePossibleStates() ;



    //grid should Should I implementation
    //add valid index checking  to Grid

    //Grid takes from the csv, initial configuration, height, width,

    //Grid creates it's own 2d array of cell based on states

    //instead of returning the grid
}
