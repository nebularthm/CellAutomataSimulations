package cellsociety;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GOLRules extends   Rules {
    private static final String ALIVE = "alive";
    private static final String DEAD = "dead";
    private static final String GOL = "Game of Life";
    private static final String RECTANGLE = "Rectangle";

    /**
     * constructor for GOLRULES objects which modulate rules for game of life simulations
     */
    public GOLRules(){

    }

    /**
     * this method changes the state of the GOL cell to the other state if the conditions are met
     * @param stat state of the cell
     * @return  state the cell will be
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
     * based on conditions, determines whether cell should update
     * @param stat state of given cell
     * @param neigbstates state of all its neigbhors
     * @return boolean for whether cell should update
     */
    @Override
    public boolean shouldUpdateCell(String stat, List<String> neigbstates) {
        if(stat.equals(DEAD)){
            return Collections.frequency(neigbstates, ALIVE) == 3 ;
        }
        else {
            return !(Collections.frequency(neigbstates, ALIVE) == 2) && !(Collections.frequency(neigbstates, ALIVE) == 3);

        }
        }

    /**
     * for the GOL- we have a full neighbhood of all the possible neighbs
     * @return strin array of all possible neigbs
     */
    @Override
    public String[] possibleNeighbs() {
        return new String[]{"up","down","left", "right", "up right", "down right", "down left", "up left"};
    }

    /**
     *
     * @return unmodifiable list representing all of the states of this simulation
     */
    @Override
    List<String> unModifiedStates() {
        List<String> validStates = new ArrayList<>();
        validStates.add(ALIVE);
        validStates.add(DEAD);
        return  Collections.unmodifiableList(validStates);
    }
}
