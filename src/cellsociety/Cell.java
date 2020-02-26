package cellsociety;

import java.util.List;

public class Cell {

    //cell should know posiiton, state, and not shape
    //Cell contains information, based on rules for neighbhorhoods, for each cell here is a list of your valid neighbhors
    //Loop through, call the canUpdate,shouuld update
    //Rule interface decides whether those should take place or not/what happens
    //Cell is just gonna need to know its state, neighbhors
    //

    private String state;
    private String nextState;
    private int xCord,yCord;
    private boolean update = false;
    private List<Cell> neighbs;

    public Cell(String stat, int y, int x){
        state = stat;
        xCord=x;
        yCord = y;
        nextState = stat;
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

    public void setNextState(String nextGenState) {
        nextState = nextGenState;
    }

    public void updateState() {state = nextState;}


    public void shouldUpdate() {
        update = !update;
    }


    public boolean canUpdate() {

        return update;
    }
}

//Connor: my earlier changes to GOLCells when it still existed, for my reference

/*
package cellsociety;


        import java.util.List;

public class GOLCells implements Cell {
    private int state;
    private List<Cell> neighbs;
    private String shape;
    private boolean update;
    private int myXPos;
    private int myYPos;
    public GOLCells(int stat, int xPos, int yPos){
        state = stat;
        myXPos = xPos;
        myYPos = yPos;

    }

    public void setState(String stat) {
        if(update == false){
            return;
        }
        state = stat;

    }



    @Override
    public void setNeighbhors(List<Cell> neighbors) {
        neighbs = neighbors;
    }

    @Override
    public void setShape(String shap) {
        shape = shap;
    }

    @Override
    public String[] getNeighbs() {
        return new String[0];
    }

    @Override
    public String getState() {
        return null;
    }

    @Override
    public void shouldUpdate() {
        int count = 0;
        for(Cell neighbor : neighbs) {
            if (neighbor.getState() == 1) {
                count++;
            }
        }
    }

    @Override
    public boolean canUpdate() {
        return false;
    }


}

*/
