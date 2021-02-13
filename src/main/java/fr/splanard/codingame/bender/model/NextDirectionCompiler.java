package fr.splanard.codingame.bender.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NextDirectionCompiler {

    private final List<String> priorities = Arrays.asList("SOUTH", "EAST", "NORTH", "WEST");
    private String currentDirection = "SOUTH";

    public String next(Futuramap map, int[] position, boolean breaker) {
        if( canGo( map.get(position, currentDirection), breaker ) ){
            return currentDirection;
        }
        for( String direction : priorities ){
            if( canGo( map.get(position, direction), breaker) ){
                currentDirection = direction;
                return direction;
            }
        }
        return null;
    }

    public boolean arePrioritiesInverted(){
        return priorities.get(0).equals("SOUTH");
    }

    public void invertPriorities(){
        Collections.reverse(priorities);
    }

    public String getCurrentDirection(){
        return currentDirection;
    }

    public void setCurrentDirection(String newDirection){
        currentDirection = newDirection;
    }

    private boolean canGo( char cellContent, boolean breakerMode ){
        return cellContent != '#' && (breakerMode || cellContent != 'X');
    }
}
