package fr.splanard.codingame.bender.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bender {

    private static final Map<Character, String> DIRECTION_MODIFIERS = new HashMap<>();
    static {
        DIRECTION_MODIFIERS.put('N', "NORTH");
        DIRECTION_MODIFIERS.put('S', "SOUTH");
        DIRECTION_MODIFIERS.put('E', "EAST");
        DIRECTION_MODIFIERS.put('W', "WEST");
    }

    private boolean breakerMode = false;
    private final LoopDetector loopDetector = new LoopDetector();
    private final NextDirectionCompiler nextDirectionCompiler = new NextDirectionCompiler();
    private int[] position;

    public List<String> getDirections(Futuramap map){
        List<String> directions = new ArrayList<>();
        position = map.getBendersInitialPosition();

        char cellContent = map.get(position);
        while( cellContent != '$' ){
            this.checkForLoop();
            this.applyCellEffect(cellContent, map);
            String direction = nextDirectionCompiler.next(map, position, breakerMode);
            directions.add( direction );
            position = map.nextPosition(position, direction);
            cellContent = map.get(position);
        }

        return directions;
    }

    private void applyCellEffect(char cellContent, Futuramap map){
        if( DIRECTION_MODIFIERS.containsKey(cellContent) ){
            nextDirectionCompiler.setCurrentDirection(DIRECTION_MODIFIERS.get(cellContent));
        }
        else if( cellContent == 'B' ){
            toggleBreakerMode();
        }
        else if( cellContent == 'X' && breakerMode ){
            map.set(position[0], position[1], ' ');
            loopDetector.reset();
        }
        else if( cellContent == 'I' ){
            nextDirectionCompiler.invertPriorities();
        }
        else if( cellContent == 'T' ){
            position = map.getOtherTeleporterPosition(position);
        }
    }

    private void checkForLoop(){
        Step step = new Step(position, nextDirectionCompiler.getCurrentDirection(),
                breakerMode, nextDirectionCompiler.arePrioritiesInverted() );
        if( loopDetector.isLoop(step) ){
            throw new LoopException();
        }
        loopDetector.addStep(step);
    }

    private void toggleBreakerMode(){
        breakerMode = !breakerMode;
    }
}
