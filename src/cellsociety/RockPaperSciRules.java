package cellsociety;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RockPaperSciRules extends Rules {
    private static final String ROCK = "rock";
    private static final String PAPER = "paper";
    private  static  final String SCISSORS = "scissors";
    private static final int THRESHOLD = 3;

    public RockPaperSciRules(int threshie) {
        super(threshie);
    }

    public RockPaperSciRules() {
    }

    @Override
    List<String> unModifiedStates() {
        List<String> validStates = new ArrayList<>();
        validStates.add(ROCK);
        validStates.add(PAPER);
        validStates.add(SCISSORS);
        return  Collections.unmodifiableList(validStates);
    }
    @Override
    String changeState(String stat) {
            if (stat.equals(ROCK)){
                return  PAPER;
            }
            if(stat.equals(PAPER)){
                return  SCISSORS;
            }
            return  ROCK;

    }
    @Override
    boolean shouldUpdateCell(String stat, List<String> neigbstates) {
        if (stat.equals(ROCK)){
            return Collections.frequency(neigbstates,PAPER) > THRESHOLD + thresh ;
        }
        if(stat.equals(PAPER)){
            return Collections.frequency(neigbstates,SCISSORS) > THRESHOLD + thresh;
        }
        else{
            return Collections.frequency(neigbstates,ROCK) > THRESHOLD + thresh;
        }
    }

}
