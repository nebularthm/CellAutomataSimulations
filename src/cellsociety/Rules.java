package cellsociety;

import java.util.List;

public abstract class Rules {
    /**
     * changes state of a cell based on rules
     * @param stat given state
     * @return the state the cell should go to
     */
    String changeState(String stat) {
        return null;
    }

    /**
     *
     * @param stat state of given cell
     * @param neigbstates state of all its neigbhors
     * @return
     */
    boolean shouldUpdateCell(String stat, List<String> neigbstates) {
        return false;
    }

    /**
     * possible neighbs this cell should consider
     * @return string array of possible neighbs
     */
    String[] possibleNeighbs() {
        return new String[0];
    }

    /**
     *
     * @return unmodifiable list representing all of the states of this simulation
     */
    abstract List<String> unModifiedStates();


}
