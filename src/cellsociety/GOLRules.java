package cellsociety;

import java.util.Collections;
import java.util.List;

public class GOLRules implements  Rules {
    private static final String ALIVE = "alive";
    private static final String DEAD = "dead";
    private static final String GOL = "Game of Life";
    private static final String RECTANGLE = "Rectangle";
    public GOLRules(){

    }

    /**
     * this method changes the state of the GOL cell to the other state if the conditions are met
     * @param stat
     * @return
     */
    @Override
    public String changeState(String stat) {
        if(stat.equals(ALIVE)){
            return DEAD;
        }
        else{
            return ALIVE;
        }
    }

    /**
     * based on the neighbor's states, this method updates the state of our cell
     * @param cell
     * @return
     */
    @Override
    public void setNextState(Cell cell) {
        if(cell.getState().equals(DEAD)){
            if(Collections.frequency(cell.getNeighborStates(), ALIVE) == 3) {
                cell.setNextState(ALIVE);
            }

        }
        else if(cell.getState().equals(ALIVE)) {
                if(Collections.frequency(cell.getNeighborStates(), ALIVE) != 2 && Collections.frequency(cell.getNeighborStates(), ALIVE) != 3) {
                    cell.setNextState(DEAD);
                }
            }
        }

    /**
     * for the GOL- we have a full neighbhood of all the possible neighbs
     * @return
     */
    @Override
    public String[] possibleNeighbs() {
        return new String[]{"up","down","left", "right", "up right", "down right", "down left", "up left"};
    }
}
