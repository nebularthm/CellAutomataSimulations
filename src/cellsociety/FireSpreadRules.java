package cellsociety;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FireSpreadRules extends Rules {
    private static final String EMPTY = "empty";
    private static final String TREE = "tree";
    private  static  final String BURNING = "burning";
    public FireSpreadRules(int threshie) {
        super(threshie);
    }

    @Override
    List<String> unModifiedStates() {
        List<String> validStates = new ArrayList<>();
        validStates.add(EMPTY);
        validStates.add(TREE);
        validStates.add(BURNING);
        return  Collections.unmodifiableList(validStates);
    }
    @Override
    String changeState(String stat) {
        if (stat.equals(BURNING)){
            return  EMPTY;
        }
        return  BURNING;


    }
    @Override
    boolean shouldUpdateCell(String stat, List<String> neigbstates) {
        if(stat.equals(TREE)){
            Random random = new Random();
            int prob = random.nextInt(10);
            return prob < thresh;
        }
        return stat.equals(BURNING);
    }
    @Override
    String[] possibleNeighbs() {
        return new String[]{"up","down","left", "right"};
    }
}
