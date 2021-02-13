package fr.splanard.codingame.bender.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StepTest {

    @Test
    public void initialPrioritiesBreakerOff(){
        // given: a step with priorities in the initial order and breaker mode off
        Step step = new Step(new int[]{1,1}, "SOUTH", false, false);

        // when: getting position and state
        // then: position is correct, state equals direction
        assertThat(step.getPositionAsString()).isEqualTo("1.1");
        assertThat(step.getState()).isEqualTo("SOUTH");
    }

    @Test
    public void breakerOn(){
        // given: a step with breaker mode on
        Step step = new Step(new int[]{1,1}, "WEST", true, false);

        // when: getting state
        // then: state contains "+I"
        assertThat(step.getState()).isEqualTo("WEST+B");
    }

    @Test
    public void invertedPriorities(){
        // given: a step with priorities in inverted order
        Step step = new Step(new int[]{1,1}, "NORTH", false, true);

        // when: getting state
        // then: state contains "+I"
        assertThat(step.getState()).isEqualTo("NORTH+I");
    }

    @Test
    public void invertedPrioritiesAndBreakerOn(){
        // given: a step with inverted priorities and breaker mode on
        Step step = new Step(new int[]{1,1}, "EAST", true, true);

        // when: getting state
        // then: state contains "+I"
        assertThat(step.getState()).isEqualTo("EAST+B+I");
    }

}
