package cellsociety;

import java.awt.*;
import java.io.IOException;
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
    private String myGame;

    public Simulate() {

    }

    public Simulate(CSVFileReader gameReader) throws IOException {
        gridHeight = gameReader.getHeight();
        gridWidth = gameReader.getWidth();
        myGrid = makeGrid(gameReader.readStates(), gameReader.readGame());
    }
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
        myGame = type;

    }

    public String getGameType() {return myGame;}

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public void setMyGrid(String[][] states, String type) {
        this.myGrid = makeGrid(states, type);
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
            myGame = GOL;
        }
        return retGrid;
    }

    public void step() {
        myGrid.updateCells();
        myGrid.generateNextStates();

    }

    /**
     * returns string representation of the matrix
     * @return
     */
    public String getState(int x, int y){
        return myGrid.getStringGrid()[x][y];
    }

    public void generateSimFile() throws IOException {
        CSVFileGenerator csvFileGenerator = new CSVFileGenerator(myGrid, myGame);
        csvFileGenerator.createCSVFile();
    }



}
