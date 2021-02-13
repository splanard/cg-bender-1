package fr.splanard.codingame.bender.model;

public class Step {

    private final int[] position;
    private final String direction;
    private final boolean breakerMode;
    private final boolean prioritiesInverted;

    public Step(int[] position, String direction, boolean breakerMode, boolean prioritiesInverted) {
        this.position = position;
        this.direction = direction;
        this.breakerMode = breakerMode;
        this.prioritiesInverted = prioritiesInverted;
    }

    public String getPositionAsString(){
        return position[0]+"."+position[1];
    }

    public String getState(){
        String state = direction;
        if( breakerMode ){
            state += "+B";
        }
        if( prioritiesInverted ){
            state += "+I";
        }
        return state;
    }
}
