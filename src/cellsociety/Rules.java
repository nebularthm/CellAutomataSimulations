package cellsociety;

import java.util.List;

public interface Rules {
    String changeState(String stat);
    void setNextState(Cell cell);
    String [] possibleNeighbs();



}
