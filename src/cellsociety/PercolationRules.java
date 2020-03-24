package cellsociety;

import java.util.Collections;
import java.util.List;

public class PercolationRules extends Rules {
    private static final String OPEN = "open";
    private static final String BLOCKED = "blocked";
    private  static  final String PERCOLATED = "percolated";
    /**
     *
     * @return unmodifiable list representing all of the states of this simulation
     */
    @Override
    List<String> unModifiedStates() {
        return null;
    }

    /**
     * for this kind of sim cells can only go from open to percolated
     * @param stat state of cell
     * @return state cell should be
     */
    String changeState(String stat) {
            return  PERCOLATED;
    }
    /**
     * based on conditions, determines whether cell should update
     * @param stat state of given cell
     * @param neigbstates state of all its neigbhors
     * @return boolean for whether cell should update
     */
    boolean shouldUpdateCell(String stat, List<String> neigbstates) {
        if (stat.equals(OPEN)){
            return Collections.frequency(neigbstates,PERCOLATED) >0;
        }
        return false;
    }
    /**
     * possible neighbs this cell should consider
     * @return string array of possible neighbs
     */
    String[] possibleNeighbs() {
        return new String[]{"up","down","left", "right", "up right", "down right", "down left", "up left"};
    }
}
