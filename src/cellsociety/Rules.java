package cellsociety;

import java.util.List;

public interface Rules {
    String changeState(String stat);
    boolean updateCell(String stat, List<String> neigbstates);
    String [] possibleNeighbs();



}
