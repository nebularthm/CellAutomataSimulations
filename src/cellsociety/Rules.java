package cellsociety;

import java.util.List;

public interface Rules {
    String changeState(String stat);

    boolean shouldUpdateCell(String stat, List<String> neigbstates);

    String [] possibleNeighbs();



}
