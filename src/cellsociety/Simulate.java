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


    /**
     * the constructor for this simulate
     * @param height
     * @param width
     * @param initial
     * @param type
     */
    public Simulate(int height, int width, List<List<String>> initial, String type){
        gridWidth = width;
        gridHeight = height;
        myGrid = makeGrid(initial,type);

    }
    /**
     * converts the Cells representation of the level into a 2d array list for easier modifciation
     * @param twoDArray
     * @return
     */
    public  ArrayList<ArrayList<Cell>> twoDArrayToList(Cell [][] twoDArray) {
        ArrayList<ArrayList<Cell>> list = new ArrayList<>();
        for (Cell [] array : twoDArray) {
            ArrayList<Cell> tempList = new ArrayList<>();
            for(Cell cell: array){
                tempList.add(cell);
            }
            list.add(tempList);
        }
        return list;
    }

    /**
     * makes the grid given the initial state and the type of game we are playing
     * @param initial
     * @param type
     * @return
     */
    private Grid makeGrid(List<List<String>> initial, String type) {
        Grid retGrid = null;
        if(type.equals(GOL)){
            retGrid = new GOLGrid(RECTANGLE,gridHeight,gridWidth);
            GOLCells [][] cellGrid = new GOLCells[gridHeight][gridWidth];
            for(int i = 0; i < initial.size();i++){
                for(int j = 0; j < initial.get(0).size();j++){
                cellGrid[i][j] = new GOLCells(initial.get(i).get(j),RECTANGLE);
                }
            }
            List<List<GOLCells>> cellGridList = twoDArrayToList(cellGrid);
            retGrid.setGrid(cellGridList);

        }

        return retGrid;
    }

    /**This method essentially handles updating the cells in this grid, essentially flips the update switch in one pass then updates elegible cells in the next
     * @param cellGrid The grid of interest
     */
    public  void updateCells(Grid cellGrid){
        List<List<Cell>> theGrid = cellGrid.getGrid();
        for(int i = 0; i < theGrid.size(); i++){
            for(int j = 0; j < theGrid.get(0).size();j++){
            Cell thisCell = theGrid.get(i).get(j);
            String [] neighbhorhood = thisCell.getNeighbs();
            if(updatable(neighbhorhood,theGrid,i,j,thisCell.getState())){
            thisCell.shouldUpdate();
            }

        }
        }
        for(int i = 0; i < theGrid.size();i++){
            for(int j = 0; j <theGrid.get(0).size();j++){
                Cell thisCell = theGrid.get(i).get(j);
                    if(thisCell.canUpdate()){
                        if(thisCell.getState().equals(ALIVE)){
                            thisCell.setState(DEAD);
                            thisCell.shouldUpdate();
                        }
                        else{
                            thisCell.setState(ALIVE);
                            thisCell.shouldUpdate();
                        }

                    }
            }
        }

    }

    /**
     * this metho
     * @param neighbhorhood
     * @param theGrid
     * @param i
     * @param j
     * @return
     */
    private boolean updatable(String[] neighbhorhood, List<List<Cell>> theGrid, int i, int j, String state) {
        List<String> stateList = new ArrayList<>();
        for(String nei:neighbhorhood){
            if (nei.equals(UP)) {
            if(validIndex(j+1)){
                stateList.add(theGrid.get(i).get(j+1).getState());
            }
            }
            if(nei.equals(DOWN)){
                if(validIndex(j-1)){
                    stateList.add(theGrid.get(i).get(j-1).getState());
                }

            }
            if(nei.equals(LEFT)){
                if(validIndex(i-1)){
                    stateList.add(theGrid.get(i-1).get(j).getState());
                }
            }
            if(nei.equals(RIGHT)){
                if(validIndex(i+1)){
                    stateList.add(theGrid.get(i+1).get(j).getState());
                }
            }
            if(nei.equals(UPRIGHT)){
                if(validIndex(i+ 1) && validIndex(j+1)){
                    stateList.add(theGrid.get(i+1).get(j+1).getState());
                }
            }
            if(nei.equals(UPLEFT)){
                if(validIndex(i - 1) && validIndex(j + 1)){
                    stateList.add(theGrid.get(i-1).get(j+1).getState());
                }

            }
            if(nei.equals(DOWNRIGHT)){
                if(validIndex(i + 1) && validIndex(j - 1)){
                    stateList.add(theGrid.get(i+1).get(j-1).getState());
                }

            }
            if(nei.equals(DOWNLEFT)){
                if(validIndex(i - 1) && validIndex(j - 1)){
                    stateList.add(theGrid.get(i-1).get(j-1).getState());
                }

            }

        }
        if(state.equals(DEAD)){
            return Collections.frequency(stateList, ALIVE) == 3;

        }
        else{
            return Collections.frequency(stateList, ALIVE) != 2 && Collections.frequency(stateList, ALIVE) != 3;
        }


    }

    /**
     * this method checks if an index, i, is within the bounds of our grid
     * @param i the index
     * @return
     */
    private boolean validIndex(int i) {
      return (i >= gridHeight && i >= gridWidth) || (i <= gridHeight && i <= gridWidth) ;
    }

}
