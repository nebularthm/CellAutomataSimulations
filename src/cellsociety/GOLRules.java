package cellsociety;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GOLRules extends   Rules {
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
     * @param
     * @return
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



    @Override
    List<String> unModifiedStates() {
        List<String> validStates = new ArrayList<>();
        validStates.add(ALIVE);
        validStates.add(DEAD);
        return  Collections.unmodifiableList(validStates);
    }
}
