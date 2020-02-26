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
