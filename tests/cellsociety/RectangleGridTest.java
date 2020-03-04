package cellsociety;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleGridTest {
    private CSVFileReader myFileReader = new CSVFileReader("Data/GameOfLife.csv");
    RectangleGrid testGrid = new RectangleGrid("rectangle",myFileReader.getHeight(),myFileReader.getWidth(),myFileReader.readStates(),new GOLRules());
    String [][]testInit = new String[myFileReader.getWidth()][myFileReader.getHeight()];
    Cell [] [] testCellular = new  Cell[myFileReader.getWidth()][myFileReader.getHeight()];

    RectangleGridTest() throws IOException {
    }

    @Test
    void setShape() {
        testGrid.setShape("circle");
        assertEquals("circle",testGrid.getShape());
    }
    @Test
    void updateCells() throws IOException {


        testGrid.updateCells();
        for(int i = 0; i < myFileReader.getHeight();i++){
            for(int j = 0; j < myFileReader.getWidth();j++){
                assertEquals(false,testGrid.getGrid()[i][j].canUpdate());
            }
        }
        //emulates the function of  shouldupdate if it were true
        for(int i = 0; i < myFileReader.getHeight();i++){
            for(int j = 0; j < myFileReader.getWidth();j++){
                testGrid.getGrid()[i][j].shouldUpdate();
            }
        }

        for(int i = 0; i < myFileReader.getHeight();i++){
            for(int j = 0; j < myFileReader.getWidth();j++){
                assertEquals(true,testGrid.getGrid()[i][j].canUpdate());
            }
        }


    }

    @Test
    void generateNextStates() throws IOException {
        testGrid.updateCells();//makes sure that the grid does not update if the conditions are not met
        for(int i = 0; i < myFileReader.getHeight();i++){
            for(int j = 0; j < myFileReader.getWidth();j++){
                assertEquals(false,testGrid.getGrid()[i][j].canUpdate());
            }
        }
        testGrid.generateNextStates();

        for(int i = 0; i < myFileReader.getHeight();i++){
            for(int j = 0; j < myFileReader.getWidth();j++){
                testInit[i][j] = "dead";
            }
        }
        assertEquals(testInit.length,testGrid.getStringGrid().length);
        if(testInit.equals(testGrid.getStringGrid())){
            assertEquals(1,1);
        }




    }

    @Test
    void initializeGridandGetStringGridTest() throws IOException {
        for(int i = 0; i < myFileReader.getHeight();i++){
            for(int j = 0; j < myFileReader.getWidth();j++){
                testInit[i][j] = "dead";
            }
        }
        assertEquals(testInit.length,testGrid.getStringGrid().length);
        if(testInit.equals(testGrid.getStringGrid())){
            assertEquals(1,1);
        }
    }

    @Test
    void getGrid() throws IOException {
        for(int i = 0; i < myFileReader.getHeight();i++){
            for(int j = 0; j < myFileReader.getWidth();j++){
                testCellular[i][j] = new Cell("dead",j,i);
            }
        }
        if(testCellular.equals(testGrid.getGrid())){
            assertEquals(1,1);
        }
    }

    @Test
    void getShape() {
        testGrid.setShape("circle");
        assertEquals("circle",testGrid.getShape());
    }



    @Test
    void validIndex() {
        assertEquals(false,testGrid.validIndex(-1,-19));
        assertEquals(false,testGrid.validIndex(9000,90000));
        assertEquals(true,testGrid.validIndex(1,1));
    }

    @Test
    void eligibleNeighbs() {
        List<Cell> eligible = testGrid.eligibleNeighbs(0,0);
        assertEquals(3,eligible.size());
        eligible = testGrid.eligibleNeighbs(0,1);
        assertEquals(5,eligible.size());
        eligible = testGrid.eligibleNeighbs(5,5);
        assertEquals(8,eligible.size());


    }
}