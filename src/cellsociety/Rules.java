package cellsociety;

import java.util.List;

public abstract class Rules {
    protected int thresh;
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
    abstract List<String> unModifiedStates();


}
