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

    public void step() {
        myGrid.generateNextStates();
        myGrid.updateCells();
    }


}
