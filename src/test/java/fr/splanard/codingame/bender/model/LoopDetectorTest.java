package fr.splanard.codingame.bender.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoopDetectorTest {

    private final LoopDetector loopDetector = new LoopDetector();

    @Test
    public void detectLoop(){
        // given: the detector is given a step that already existed (same position, same state)
        loopDetector.addStep(new Step(new int[]{0,0}, "SOUTH", false, false));
        loopDetector.addStep(new Step(new int[]{0,1}, "EAST", false, false));
        loopDetector.addStep(new Step(new int[]{1,1}, "NORTH", false, false));
        loopDetector.addStep(new Step(new int[]{1,0}, "WEST", false, false));

        // when: checking for a loop
        // then: return true
        assertThat(loopDetector.isLoop(new Step(new int[]{0,0}, "SOUTH", false, false))).isTrue();
    }

}
