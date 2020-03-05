package cellsociety;

import java.util.Collections;
import java.util.List;

public class PercolationRules extends Rules {
    private static final String OPEN = "open";
    private static final String BLOCKED = "blocked";
    private  static  final String PERCOLATED = "percolated";
    @Override
    List<String> unModifiedStates() {
        return null;
    }

    /**
     * for this kind of sim cells can only go from open to percolated
     * @param stat
     * @return
     */
    String changeState(String stat) {
            return  PERCOLATED;
    }

    boolean shouldUpdateCell(String stat, List<String> neigbstates) {
        if (stat.equals(OPEN)){
            return Collections.frequency(neigbstates,PERCOLATED) >0;
        }
        return false;
    }

    String[] possibleNeighbs() {
        return new String[]{"up","down","left", "right", "up right", "down right", "down left", "up left"};
    }
}
