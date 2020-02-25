package cellsociety;

public class GOLCells   {

    private String state;
    private String [] neighbs;
    private String shape;
    private boolean update = false;
    public GOLCells(String stat, String shap){

        state = stat;
        shape = shap;
    }

    public void setState(String stat) {
        state = stat;
    }


    public void setNeighbhors() {
        neighbs = new String[]{"up","down","left", "right", "up right", "down right", "down left", "up left"};
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
