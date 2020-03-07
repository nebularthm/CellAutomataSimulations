package cellsociety;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SegregationRules extends Rules {
    private static final String POC = "poc";
    private static final String EMPTY = "empty";
    private  static  final String WHITES = "white";
    private List emptyStateList;
    private List<Integer> seenIndices;

    public SegregationRules() {

    }

    public SegregationRules(int threshie) {
        super(threshie);
    }
    @Override
    public void setStateList(){
        emptyStateList = theGrid.allCellsWithState(EMPTY);
    }

    @Override
    public boolean isMovingCells(){
        return true;
    }
    @Override
    public boolean shouldMoveCell(Cell cell){
    return cell.canMove();
    }
    @Override
    public Cell getDestinationCell(Cell cell){
        int indy = 0;
        do {
            indy = (int)(Math.random() * emptyStateList.size());
        }
        while (!seenIndices.contains(indy));
        seenIndices.add(indy);
        return (Cell) emptyStateList.get(indy);

    }


    @Override
    List<String> unModifiedStates() {
        List<String> validStates = new ArrayList<>();
        validStates.add(EMPTY);
        validStates.add(WHITES);
        validStates.add(POC);
        return  Collections.unmodifiableList(validStates);
    }
    @Override
    String changeState(String stat) {
        return stat;
    }
    @Override
    boolean shouldUpdateCell(String stat, List<String> neigbstates) {
        double threshold = thresh * 3 /10;
        return Collections.frequency(neigbstates,stat)/neigbstates.size() <= threshold;
    }
    @Override
    public void resetStateList() {
        System.out.println(emptyStateList);
        emptyStateList.clear();
        seenIndices.clear();
        emptyStateList = theGrid.allCellsWithState(EMPTY);
    }
}
