package cellsociety;

import java.util.List;

public class Cell {
    private String state;
    private int xCord,yCord;
    private boolean update = false;
    private List<Cell> neighbs;

    /**
     *  creates a ceell based on followin parameters
     * @param stat the state of this cell
     * @param y the y position of the cell
     * @param x the x position of the cell
     */
    public Cell(String stat, int y, int x){
        state = stat;
        xCord=x;
        yCord = y;
    }

    /**
     * sets the state of the cell
     * @param stat state we changin to
     */
    public void setState(String stat) {
        state = stat;
    }

    /**
     * sets the valid neighbors for a cell
     * @param neighb list of valid neighbs for this cell
     */
    public void setNeighbhors(List<Cell> neighb) {
        neighbs = neighb;
    }

    /**
     *
     * @return the list of string states of the neighbhors for this cell
     */
    public List<String> getNeighborStates() {
        List<String> neighborStates = null;
        for(Cell neighbor : neighbs) {
            neighborStates.add(neighbor.getState());
        }
        return neighborStates;
    }

    /**
     *
     * @return the state of this cell
     */
    public String getState() {
        return state;
    }

    /**
     * if a condition is meant, this cell will be marked as being updatable
     */
    public void shouldUpdate() {
        update = !update;
    }

    /**
     *
     * @return boolean representin whether cell should update
     */
    public boolean canUpdate() {
        return update;
    }

    /**
     *
     * @return the list of eligible neighbors of this cell
     */
    public List<Cell> getNeighbs() {
        return neighbs;
    }

}







