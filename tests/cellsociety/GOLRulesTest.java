package cellsociety;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GOLRulesTest {
    GOLRules testGOLRUles = new GOLRules();
    @Test
    void changeState() {
        assertEquals("alive",testGOLRUles.changeState("dead"));
        assertEquals("dead",testGOLRUles.changeState("alive"));
    };

    @Test
    void shouldUpdateCell() {
        List<String>  alive3 = new ArrayList<>();
        alive3.add("alive");
        alive3.add("alive");
        alive3.add("alive");
        List<String> alive2 = new ArrayList<>(alive3);
        alive2.remove(0);
        List<String> alive4 = new ArrayList<>(alive3);
        alive4.add("alive");
        assertEquals(false,testGOLRUles.shouldUpdateCell("dead",alive4));
        assertEquals(false,testGOLRUles.shouldUpdateCell("dead",alive2));
        assertEquals(true,testGOLRUles.shouldUpdateCell("dead",alive3));
        assertEquals(true,testGOLRUles.shouldUpdateCell("alive",alive4));
        assertEquals(false,testGOLRUles.shouldUpdateCell("alive",alive2));
        assertEquals(false,testGOLRUles.shouldUpdateCell("alive",alive3));


    }
}