package cellsociety;

import java.util.ArrayList;
import java.util.List;


/***
 * This is an abstract class that creates a model representation of a grid with a full neighborhood for a simulation.
 * States in the grid can be accessed, modified and updated in this class. The plan for this abstract class was to have a subclass
 * for each cell shape (Triangle, Rectangle, Hexagon, etc) so that the neighborhood differences could be dealt with.
 * @param <T> I did not create this parameter
 *
 * @author Michael Williams
 * @author Connor Penny
 */

public abstract class Grid<T> {


    private static final String ALIVE = "alive";
    private static final String DEAD = "dead";
    private static final String GOL = "Game of Life";
    private static final String RECTANGLE = "Rectangle"; //These should be deleted


    private String shape; //This should be deleted

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

    /***
     * Using the rules instance variable for a specific simulation, iterate through the cells in the grid on a first pass and determine what cells
     * need to update, storing that information in the cell (THIS SHOULD BE ABSTRACT)
     */
    void updateCells() {


    }

    /***
     * Change a specific cell in the grid to a different state (THIS SHOULD BE ABSTRACT)
     * @param xPos x coordinate of the cell
     * @param yPos y coordinate of the cell
     */
    void changeStateSingleCell(int xPos, int yPos){

    }

    /***
     * Using the rules for a given simulation, iterate through the cells in the grid on a second pass and update their states to the next
     * generation
     */
    abstract void generateNextStates();

    /***
     * Generates a 2D array of cell objects to represent the grid for a simulation
     * @param initialStates a 2D array of the states for the simulation grid
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
