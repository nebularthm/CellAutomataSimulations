package cellsociety;

import java.util.List;

public class Cell {
    private String state;
    private int xCord,yCord;
    private boolean update = false;
    private List<Cell> neighbs;
    private boolean move = false;

    public Cell(String stat, int y, int x){
        state = stat;
        xCord=x;
        yCord = y;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public void setState(String stat) {
        state = stat;
    }


    public void setNeighbhors(List<Cell> neighb) {
        neighbs = neighb;
    }


    public List<String> getNeighborStates() {
        List<String> neighborStates = null;
        for(Cell neighbor : neighbs) {
            neighborStates.add(neighbor.getState());
        }
        return neighborStates;
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
    public List<Cell> getNeighbs() {
        return neighbs;
    }

    public void shouldMove(){
        move = !move;
    }

    public boolean canMove(){
        return move;
    }

}







