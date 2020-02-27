package cellsociety;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Michael Williams
 */
class CellTest {
    private Cell testCell = new Cell("dead",0,0);
    private Cell cell1 = new Cell("dead",1,1);
    private Cell cell2 = new Cell("dead",1,0);
    private Cell cell3 = new Cell("dead",0,1);
    private List<Cell> controlList = new ArrayList<>();

    @Test
    void setState() {
        testCell.setState("foo");
        assertEquals("foo",testCell.getState());
    }

    @Test
    void setNeighbhors() {
        List<Cell> testList = new ArrayList<>();
        testList.add(cell1);
        testList.add(cell2);
        testList.add(cell3);
        testCell.setNeighbhors(testList);
        assertEquals(testList,testCell.getNeighbs());
    }

    @Test
    void getNeighbs() {
        controlList.add(cell1);
        controlList.add(cell2);
        controlList.add(cell3);
        testCell.setNeighbhors(controlList);
        assertEquals(controlList,testCell.getNeighbs());
    }

    @Test
    void getState() {
        assertEquals("dead",testCell.getState());
    }

    @Test
    void shouldUpdate() {
        testCell.shouldUpdate();
        assertEquals(true,testCell.canUpdate());
    }

    @Test
    void canUpdate() {
        assertEquals(false,testCell.canUpdate());

    }
}