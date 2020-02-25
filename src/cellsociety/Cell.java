package cellsociety;

public class Cell {

    //cell should know posiiton, state, and not shape
    //Cell contains information, based on rules for neighbhorhoods, for each cell here is a list of your valid neighbhors
    //Loop through, call the canUpdate,shouuld update
    //Rule interface decides whether those should take place or not/what happens
    //Cell is just gonna need to know its state, neighbhors
    //

    private String state;
    private String [] neighbs;
    private int xCord,yCord;
    private boolean update = false;
    public Cell(String stat, int x, int y){
        state = stat;
        xCord=x;
        yCord = y;
    }

    public void setState(String stat) {
        state = stat;
    }


    public void setNeighbhors(String [] neighb) {
        neighbs = neighb;
                //new String[]{"up","down","left", "right", "up right", "down right", "down left", "up left"};
    }




    public String[] getNeighbs() {
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
