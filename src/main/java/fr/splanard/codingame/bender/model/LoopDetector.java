package fr.splanard.codingame.bender.model;

import java.util.HashMap;
import java.util.Map;

public class LoopDetector {

    private Map<String,String> steps = new HashMap<>();
    
    public boolean isLoop(Step step){
        String position = step.getPositionAsString();
        return steps.containsKey(position) && steps.get(position).equals(step.getState());
    }

    public void addStep(Step step){
        steps.put(step.getPositionAsString(), step.getState());
    }

    public void reset(){
        steps = new HashMap<>();
    }

}
