package cellsociety;

import java.util.List;
//Based on whether or not it is a triangle a square, it'll have a different config, grid knows how to access correct neighbhors for shape, Cell just needs to know what it's neigh hors are
//The rules shouldnt change based on shapes
//does cell even need to know it's neighhors

public class GOLGrid implements  Grid {
    private String shape;
    private int width,height;
    private List<List<GOLCells>> myGrid;
    public GOLGrid(String shap, int wid, int hei){
        shape = shap;
        width = wid;
        height = hei;
    }

    @Override
    public void setShape() {

    }

    @Override
    public void updateCells() {
        List<List<Cell>> theGrid = this.getGrid();
        for(int i = 0; i < theGrid.size(); i++){
            for(int j = 0; j < theGrid.get(0).size();j++){
                Cell thisCell = theGrid.get(i).get(j);
                String [] neighbhorhood = thisCell.getNeighbs();
                if(updatable(neighbhorhood,theGrid,i,j,thisCell.getState())){
                    thisCell.shouldUpdate();
                }

            }
        }
        for(int i = 0; i < theGrid.size();i++){
            for(int j = 0; j <theGrid.get(0).size();j++){
                Cell thisCell = theGrid.get(i).get(j);
                if(thisCell.canUpdate()){
                    if(thisCell.getState().equals(ALIVE)){
                        thisCell.setState(DEAD);
                        thisCell.shouldUpdate();
                    }
                    else{
                        thisCell.setState(ALIVE);
                        thisCell.shouldUpdate();
                    }


                }
            }
        }
    }

    @Override
    public void setUpdateRules() {

    }

    @Override
    public void setGrid(List cells) {

    }



    @Override
    public List<List> getGrid() {
        return null;
    }

    @Override
    public String getShape() {
        return null;
    }

    @Override
    public void setWidth(int height) {

    }

    @Override
    public void setHeight(int width) {

    }


}
