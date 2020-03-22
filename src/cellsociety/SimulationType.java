package cellsociety;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/***
 * A class that enumerates all the simulation types and holds their states as a list
 * Although not implemented now, this could be used in the view so that information about the states
 * of each simulation does not have to be passed to the view from the model
 * @author Connor Penny
 */
public enum SimulationType {
    GOL("Game of Life", new String[]{"alive", "dead"}),
    PERCOLATION("Percolation", new String[]{"open", "blocked", "percolated"}),
    RPS("Rock Paper Scissors", new String[]{"rock", "paper", "scissors"}),
    FIRE("Spreading of Fire", new String[]{}),
    SEGREGATION("Segregation", new String[]{}),
    PREDPREY("Predator-Prey", new String[]{});

    String myName;
    List<String> myStates;

    SimulationType(String name, String[] states) {
        myName = name;
        myStates = Arrays.asList(states);
    }

    /***
     * Access to a list of the states for a simulation (not modifiable)
     * @return list of states for a simulation
     */
    public List<String> getImmutableStates() {
        return  Collections.unmodifiableList(myStates);
    }

    /***
     * Determines if two simulations are the same type
     * @param name the name of the simulation
     * @return if the simulations are the same type boolean
     */
    public boolean equals(String name) {
        return (myName.equals(name));

    }

    /***
     * Prints the name of a simulation type
     * @return the name of the simulation type as a string
     */
    public String toString() {
        return myName;
    }



}
