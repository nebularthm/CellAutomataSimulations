package cellsociety;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    public List<String> getImmutableStates() {
        return  Collections.unmodifiableList(myStates);
    }

    public boolean equals(String name) {
        return (myName.equals(name));

    }
    public String toString() {
        return myName;
    }



}
