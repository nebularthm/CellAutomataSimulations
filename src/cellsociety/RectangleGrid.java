package cellsociety;

import java.util.ArrayList;
import java.util.List;
//Based on whether or not it is a triangle a square, it'll have a different config, grid knows how to access correct neighbhors for shape, Cell just needs to know what it's neigh hors are
//The rules shouldnt change based on shapes
//does cell even need to know it's neighhors

public class RectangleGrid extends   Grid {
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



    public RectangleGrid(String shap, int wid, int hei, String[][] initialStates, Rules rules){
        super(shap,wid,hei,initialStates,rules);
        initializeNeighbhors();


    }
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

    public void updateCells() {
        for(int yPos = 0; yPos<gridHeight; yPos++) {
            for(int xPos = 0; xPos<gridWidth; xPos++) {
                if(theRules.shouldUpdateCell(myGrid[yPos][xPos].getState(), neighbsToString(myGrid[yPos][xPos].getNeighbs()))){//pass the cell directly into rulles
                    myGrid[yPos][xPos].shouldUpdate();
                    if(theRules.isMovingCells() && theRules.shouldMoveCell(myGrid[yPos][xPos])){
                        myGrid[yPos][xPos].shouldMove();
                    }
                }

            }
        }
    }

    public void generateNextStates() {
        for(int yPos = 0; yPos<this.getGridHeight(); yPos++) {
            for(int xPos = 0; xPos<this.getGridWidth(); xPos++) {
                if(myGrid[yPos][xPos].canUpdate()){
                    if(myGrid[yPos][xPos].canMove()){
                        swap(myGrid[yPos][xPos],theRules.getDestinationCell(myGrid[yPos][xPos]));
                        myGrid[yPos][xPos].shouldUpdate();
                        myGrid[yPos][xPos].shouldMove();
                    }
                    else {
                        myGrid[yPos][xPos].setState(theRules.changeState(myGrid[yPos][xPos].getState()));
                        myGrid[yPos][xPos].shouldUpdate();
                    }
                }
            }
        }
        theRules.resetStateList();

    }

    private void swap(Cell cell, Cell destinationCell) {
        Cell temp = cell;
        cell.setyCord(destinationCell.getyCord());
        cell.setxCord(destinationCell.getxCord());
        cell.setState(destinationCell.getState());
        destinationCell.setyCord(temp.getyCord());
        destinationCell.setxCord(temp.getxCord());
        destinationCell.setState(temp.getState());

        myGrid[cell.getyCord()][cell.getxCord()] = cell;
        myGrid[destinationCell.getyCord()][destinationCell.getxCord()] = destinationCell;

    }


    public  void changeStateSingleCell(int xPos,int yPos){
       myGrid[yPos][xPos].setState(theRules.changeState(myGrid[yPos][xPos].getState()));
    }






    public String getState(int x, int y) {
        return myGrid[y][x].getState();
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
