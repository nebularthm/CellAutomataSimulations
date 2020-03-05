package cellsociety;

public enum SimulationType {
    GOL("Game of Life"),
    PERCOLATION("Percolation"),
    RPS("Rock Paper Scissors"),
    FIRE("Spreading of Fire"),
    SEGREGATION("Segregation"),
    PREDPREY("Predator-Prey");

    String myName;

    SimulationType(String name) {
        myName = name;
    }

    public boolean equals(String name) {
        return (myName == name);

    }
    public String toString() {
        return myName;
    }



}
