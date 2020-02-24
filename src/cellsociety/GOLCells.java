package cellsociety;



public class GOLCells implements Cell {
    private String state;
    private String [] neighbs;
    private String shape;
    private boolean update;
    public GOLCells(String stat, String[] nei, String shap){
        state = stat;
        neighbs = nei;
        shape = shap;
    }
    
    public void setState(String stat) {
        if(update == false){
            return;
        }
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
    public void shouldUpDate(){
        update = !update;
    }
}
