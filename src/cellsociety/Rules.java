package cellsociety;

import java.util.List;

public abstract class Rules {
    String changeState(String stat) {
        return null;
    }

    boolean shouldUpdateCell(String stat, List<String> neigbstates) {
        return false;
    }

    String[] possibleNeighbs() {
        return new String[]{"up","down","left", "right", "up right", "down right", "down left", "up left"};
    }
    abstract List<String> unModifiedStates();


}
