package cellsociety;

import java.io.IOException;
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

    /**
     *default constructor, no parameters, used for exception testing
     */
    public Simulate() {

    }

    /**
     * constructor for simulate object
     * @param gameReader reads the game from the csv file
     * @param gameType type of simulation we are dealing with
     * @throws IOException from CSVFILeReader object
     */
    public Simulate(CSVFileReader gameReader, String gameType) throws IOException {
        gridHeight = gameReader.getHeight();
        gridWidth = gameReader.getWidth();
        myGrid = makeGrid(gameReader.readStates(), gameType);
    }
    /**
     * the constructor for this simulate objecct based on the followin params
     *
     * @param height height of the sim
     * @param width width of the sim
     * @param initial initial states of the sims
     * @param type type of simulation this is
     */

    public Simulate(int height, int width, String[][] initial, String type) {
        gridWidth = width;
        gridHeight = height;
        myGrid = makeGrid(initial, type);
        myGame = type;
    }

    public String getGameType() {return myGame;}

    /**
     *
     * @return height of the grid
     */
    public int getGridHeight() { return myGrid.getGridHeight();}

    /**
     *
     * @return the width of the grid
     */
    public int getGridWidth() { return myGrid.getGridWidth();}

    public void setMyGrid(String[][] states, String type) {
        this.myGrid = makeGrid(states, type);
    }

    /**
     * makes the grid given the initial state and the type of game we are playing
     *
     * @param initial initial state config
     * @param type type of the simulation
     * @return grid of the simulation
     */
    private Grid makeGrid(String[][] initial, String type) {
        Grid retGrid = null;
        if (type.equals(GOL)) {

            retGrid = new RectangleGrid(RECTANGLE, gridWidth, gridHeight, initial, new GOLRules());
            myGame = GOL;
        }
        return retGrid;
    }

    /**
     * steps thhrouh animatin simulation, updates the cells of this simulation
     */
    public void step() {
        myGrid.updateCells();
        myGrid.generateNextStates();
    }

    /**
     *
     * @return state of a cell at given x,y coordinate
     */
    public String getState(int x, int y){
        return myGrid.getState(x,y);
    }

    public void rotateState(int x, int y) {myGrid.changeStateSingleCell(x, y);}

    public void generateSimFile(String filename) throws IOException {
        CSVFileGenerator csvFileGenerator = new CSVFileGenerator(myGrid);
        csvFileGenerator.createCSVFile(filename);
    }

    /**
     *
     * @return unmodifiable list of possible states for this simulation
     */
    public List<String> getUnmodifiablePossibleStates() {
        return myGrid.getUnmodifiablePossibleStates();
    }

    public void generatePropertiesFile() throws IOException{

    }
}
