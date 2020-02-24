package cellsociety;

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
    private int gridHeight;
    private int gridWidth;
    private Grid myGrid;
    /**This method essentially handles updating the cells in this grid
     * @param cellGrid The grid of interest
     */
    public  void updateCells(Grid cellGrid){
        List<List<Cell>> theGrid = cellGrid.getGrid();
        for(int i = 0; i < theGrid.size(); i++){
            for(int j = 0; j < theGrid.get(0).size();j++){
            Cell thisCell = theGrid.get(i).get(j);
            String [] neighbhorhood = thisCell.getNeighbs();

        }
        }

    }

}
