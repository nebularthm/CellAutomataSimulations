package cellsociety;

import java.io.IOException;
import java.util.List;

/**
 * @author  Michael Williams
 */
public class Simulate {

    private static final String GOL = "Game of Life";
    private static final String RECTANGLE = "Rectangle";
    private static final String PERC = "Percolation" ;
    private static final String RPS ="RPS" ;
    private static final String FIRE ="Spreading of Fire";
    private static final String RACISM = "Segregation";
    private int gridHeight;
    private int gridWidth;
    private Grid myGrid;
    private String myGame;
    private double myThreshold;
    public Simulate() {

    }

    public Simulate(CSVFileReader gameReader, String gameType) throws IOException {
        gridHeight = gameReader.getHeight();
        gridWidth = gameReader.getWidth();
        myGrid = makeGrid(gameReader.readStates(), gameType);
        myThreshold = gameReader.getThreshold();
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

    public int getGridHeight() { return myGrid.getGridHeight();}

    public int getGridWidth() { return myGrid.getGridWidth();}

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

            retGrid = new RectangleGrid(RECTANGLE, gridWidth, gridHeight, initial, new GOLRules());
            myGame = GOL;
        }
        if(type.equals(PERC)){
            myGame = PERC;
            retGrid = new RectangleGrid(RECTANGLE, gridWidth, gridHeight, initial, new PercolationRules());
        }
        if(type.equals(RPS)){
            myGame = RPS;
            retGrid = new RectangleGrid(RECTANGLE, gridWidth, gridHeight, initial, new RockPaperSciRules((int)myThreshold));
        }
        if(type.equals(FIRE)){
            myGame = FIRE;
            retGrid = new RectangleGrid(RECTANGLE, gridWidth, gridHeight, initial, new FireSpreadRules((int)myThreshold));
        }
        if(type.equals(RACISM)){
            myGame = RACISM;
            retGrid = new RectangleGrid(RECTANGLE, gridWidth, gridHeight, initial, new SegregationRules((int)myThreshold));
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
        return myGrid.getState(x,y);
    }

    public void rotateState(int x, int y) {myGrid.changeStateSingleCell(x, y);}

    public void generateSimFile(String filename) throws IOException {
        CSVFileGenerator csvFileGenerator = new CSVFileGenerator(myGrid);
        csvFileGenerator.createCSVFile(filename);
    }

    public List<String> getUnmodifiablePossibleStates() {
        return myGrid.getUnmodifiablePossibleStates();
    }

    public void generatePropertiesFile() throws IOException{

    }
}
