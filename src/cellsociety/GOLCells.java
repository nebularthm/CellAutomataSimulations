package cellsociety;



public class GOLCells implements Cell {
    private String state;
    private String [] neighbs;
    private String shape;
    public GOLCells(String stat, String[] nei, String shap){
        state = stat;
        neighbs = nei;
        shape = shap;
    }


    @Override
    public void setState() {

    }

    @Override
    public void setNeighbhors() {

    }

    @Override
    public void setShape() {

    }
}
