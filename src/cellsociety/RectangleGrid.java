package cellsociety;

import java.util.ArrayList;
import java.util.List;
//Based on whether or not it is a triangle a square, it'll have a different config, grid knows how to access correct neighbhors for shape, Cell just needs to know what it's neigh hors are
//The rules shouldnt change based on shapes
//does cell even need to know it's neighhors


/***
 * Subclass of the abstract Grid class that creates a model representation of a grid of rectangles with a full neighborhood for a simulation.
 * States in the grid can be accessed, modified and updated in this class.
 * @author Michael Williams
 * @author Connor Penny
 */
public class RectangleGrid extends Grid {
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String UPRIGHT = "up right";
    private static final String UPLEFT = "up left";
    private static final String DOWNRIGHT = "down right";
    private static final String DOWNLEFT = "down left";

//    private String shape;
//
//    private int gridHeight;
//    private int gridWidth;
//    private Cell[][] myGrid;
//    private Rules theRules;

    /***
     * Constructor for a grid of rectangles for a given simulation
     * @param shap unnecessary parameter representing the shape of the cells in the grid (should have been deleted)
     * @param wid width of the grid
     * @param hei height of the grid
     * @param initialStates 2D array representing the grid for a simulation
     * @param rules the given rules object that allows the grid cells to update correctly
     */
    public RectangleGrid(String shap, int wid, int hei, String[][] initialStates, Rules rules){
        super(shap,wid,hei,initialStates,rules);
        initializeNeighbhors();


    }

    /***
     * Set the list of eligible neighbors for each cell in the list
     */
    public void initializeNeighbhors(){
        for(int yPos = 0; yPos<gridHeight; yPos++) {
            for(int xPos = 0; xPos<gridWidth; xPos++) {
                List<Cell> cellNeighbors = eligibleNeighbs(yPos,xPos);
                myGrid[yPos][xPos].setNeighbhors(cellNeighbors);
            }

        }

    }


    private List<String> neighbsToString(List<Cell> cellList){
        List<String> stateList = new ArrayList<>();
        for(Cell cell:cellList){
            stateList.add(cell.getState());
        }
        return stateList;
    }



    /***
     * Using the rules instance variable for a specific simulation, iterate through the cells in the grid on a first pass and determine what cells
     * need to update, storing that information in the cell
     */


    public void updateCells() {
        for(int yPos = 0; yPos<gridHeight; yPos++) {
            for(int xPos = 0; xPos<gridWidth; xPos++) {
                if(theRules.shouldUpdateCell(myGrid[yPos][xPos].getState(), neighbsToString(myGrid[yPos][xPos].getNeighbs()))){//pass the cell directly into rulles
                    myGrid[yPos][xPos].shouldUpdate();
                }

            }
        }
    }


    /***
     * Using the rules for a given simulation, iterate through the cells in the grid on a second pass and update their states to the next
     * generation
     */

    public void generateNextStates() {
        for(int yPos = 0; yPos<this.getGridHeight(); yPos++) {
            for(int xPos = 0; xPos<this.getGridWidth(); xPos++) {
                if(myGrid[yPos][xPos].canUpdate()){
                    myGrid[yPos][xPos].setState(theRules.changeState(myGrid[yPos][xPos].getState()));
                    myGrid[yPos][xPos].shouldUpdate();
                }
            }
        }

    }


    /***
     * Change a specific cell in the grid to a different state
     * @param xPos x coordinate of the cell
     * @param yPos y coordinate of the cell
     */

   public  void changeStateSingleCell(int xPos,int yPos){
       myGrid[yPos][xPos].setState(theRules.changeState(myGrid[yPos][xPos].getState()));
    }






    public String getState(int x, int y) {
        return myGrid[y][x].getState();
    }

    @Override
    public List<String> getUnmodifiablePossibleStates() {
        return theRules.unModifiedStates();
    }
    //Connor: I think we're gonna have to alter this method to just return a given state within the grid because it says
    // in the instructions that there should be no public references to the model's data structure for the grid (i.e. the 2D array we return here)

    public String[][] getStringGrid() {
        Cell[][] gridBeforeConv = this.getGrid();
        String[][] stringGrid = new String[gridWidth][gridHeight];
        for (int i = 0; i < gridBeforeConv.length; i++) {
            for (int j = 0; j < gridBeforeConv[0].length; j++) {
                stringGrid[i][j] = gridBeforeConv[i][j].getState();
            }
        }

        return stringGrid;
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
