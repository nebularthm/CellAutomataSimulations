package cellsociety;

import java.util.List;

public abstract class Rules {
    protected int thresh;
    protected  Grid theGrid;
    public  Rules(){
        thresh = 0;
    }
    public  Rules(int threshie){
        thresh = threshie;
    }
    String changeState(String stat) {
        return null;
    }

    boolean shouldUpdateCell(String stat, List<String> neigbstates) {
        return false;
    }

    String[] possibleNeighbs() {
        return new String[]{"up","down","left", "right", "up right", "down right", "down left", "up left"};
    }
    void setThresh(int threshie){
        thresh = threshie;
    }
    boolean isMovingCells(){
        return false;
    }
    abstract List<String> unModifiedStates();

    public void setStateList(){

    }
    public boolean shouldMoveCell(Cell cell) {
     return false;
    }

    public Cell getDestinationCell(Cell cell) {
        return null;
    }

    public void resetStateList() {
    }
    public  void setTheGrid(Grid griddy){
        theGrid = griddy;
    }
}
