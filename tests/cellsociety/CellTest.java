package cellsociety;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    private Cell testCell = new Cell("dead",0,0);
    @Test
    void setState() {
        testCell.setState("foo");
        assertEquals("foo",testCell.getState());
    }

    @Test
    void setNeighbhors() {
        List<Cell> testList = new ArrayList<>();
    }

    @Test
    void getNeighbs() {
    }

    @Test
    void getState() {
    }

    @Test
    void shouldUpdate() {
    }

    @Test
    void canUpdate() {
    }
}