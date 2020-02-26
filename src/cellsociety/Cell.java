package cellsociety;

import java.util.List;

public class Cell {
    private String state;
    private int xCord,yCord;
    private boolean update = false;
    private List<Cell> neighbs;
    public Cell(String stat, int x, int y){
        state = stat;
        xCord=x;
        yCord = y;
    }

    public void setState(String stat) {
        state = stat;
    }


    public void setNeighbhors(List<Cell> neighb) {
        neighbs = neighb;
    }


    public List<Cell> getNeighbs() {
        return neighbs;
    }


    public String getState() {
        return state;
    }

    public void shouldUpdate() {
        update = !update;
    }


    public boolean canUpdate() {
        return update;
    }
}







