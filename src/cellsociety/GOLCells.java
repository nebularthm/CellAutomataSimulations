package cellsociety;



public class GOLCells implements Cell {
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



    @Override
    public void setNeighbhors() {
        neighbs = new String[]{"up","down","left", "right", "up right", "down right", "down left", "up left"};
    }

    @Override
    public void setShape(String shap) {
        shape = shap;
    }

    @Override
    public String[] getNeighbs() {
        return neighbs;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getShape() {
        return shape;
    }

    @Override
    public void shouldUpdate() {
    update = !update;
    }

    @Override
    public boolean canUpdate() {

        return update;
    }


}
