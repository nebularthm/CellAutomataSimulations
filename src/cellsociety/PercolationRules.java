package cellsociety;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PercolationRules extends Rules {
    private static final String OPEN = "open";
    private static final String BLOCKED = "blocked";
    private  static  final String PERCOLATED = "percolated";
    @Override
    List<String> unModifiedStates() {
        List<String> validStates = new ArrayList<>();
        validStates.add(OPEN);
        validStates.add(BLOCKED);
        validStates.add(PERCOLATED);
        return  Collections.unmodifiableList(validStates);
    }

    /**
     * for this kind of sim cells can only go from open to percolated
     * @param stat
     * @return
     */
    @Override
    String changeState(String stat) {
            return  PERCOLATED;
    }
    @Override
    boolean shouldUpdateCell(String stat, List<String> neigbstates) {
        if (stat.equals(OPEN)){
            return Collections.frequency(neigbstates,PERCOLATED) >0;
        }
        return false;
    }


}
