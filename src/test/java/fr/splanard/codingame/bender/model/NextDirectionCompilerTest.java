package fr.splanard.codingame.bender.model;

import fr.splanard.codingame.bender.model.builder.FuturamapBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NextDirectionCompilerTest {

    private final NextDirectionCompiler compiler = new NextDirectionCompiler();
    private final int[] position = {1, 1};

    @Test
    public void directionPriorities_southFirst(){
        // given: a map where the south position is free (empty map)
        Futuramap map = FuturamapBuilder.fromString("   \n   \n   ");

        // when: compiling the next direction
        String next = compiler.next(map, position,false);

        // then: next direction is SOUTH
        assertThat(next).isEqualTo("SOUTH");
    }

    @Test
    public void directionPriorities_secondEast(){
        // given: a map where the south position is blocked
        Futuramap map = FuturamapBuilder.fromString("   \n   \n # ");

        // when: compiling the next direction
        String next = compiler.next(map, position, false);

        // then: next direction is EAST
        assertThat(next).isEqualTo("EAST");
    }

    @Test
    public void directionPriorities_thirdNorth(){
        // given: a map where the south and east positions are blocked
        Futuramap map = FuturamapBuilder.fromString("   \n  #\n # ");

        // when: compiling the next direction
        String next = compiler.next(map, position, false);

        // then: next direction is NORTH
        assertThat(next).isEqualTo("NORTH");
    }

    @Test
    public void directionPriorities_fourthWest(){
        // given: a map where the south, east and north positions are blocked
        Futuramap map = FuturamapBuilder.fromString(" # \n  #\n # ");

        // when: compiling the next direction
        String next = compiler.next(map, position, false);

        // then: next direction is WEST
        assertThat(next).isEqualTo("WEST");
    }

    @Test
    public void breakableObstacles(){
        // given: south, east and north positions are blocked with a breakable obstacle
        Futuramap map = FuturamapBuilder.fromString(" X \n  X\n X ");

        // when: compiling the next direction
        String next = compiler.next(map, position, false);

        // then: next direction is WEST
        assertThat(next).isEqualTo("WEST");
    }

    @Test
    public void breakerMode(){
        // given: breaker mode is ON and there is an obstacle south
        Futuramap map = FuturamapBuilder.fromString("   \n   \n X ");

        // when: compiling the next direction
        // then: next direction is SOUTH
        assertThat(compiler.next(map, position, true)).isEqualTo("SOUTH");
    }

    @Test
    public void invertPriorities_firstWest(){
        // given: priorities are inverted, south is blocked
        Futuramap map = FuturamapBuilder.fromString("   \n   \n # ");

        // when: inverting priorities, then compiling the next direction
        compiler.invertPriorities();
        String nextDirection = compiler.next(map, position, false);

        // then: next direction is WEST
        assertThat(nextDirection).isEqualTo("WEST");
    }

}
