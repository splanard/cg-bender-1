package fr.splanard.codingame.bender.model;

import fr.splanard.codingame.bender.model.builder.FuturamapBuilder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BenderTest {

    private final Bender bender = new Bender();

    @Test
    public void straightToSouth(){
        // given: a map where the suicide cabin is just one step south
        Futuramap map = FuturamapBuilder.fromString("   \n @ \n $ ");

        // when: getting directions from bender
        List<String> directions = bender.getDirections(map);

        // then: directions contains only one step south
        assertThat(directions).isEqualTo(Collections.singletonList("SOUTH"));
    }

    @Test
    public void firstSouthThenEast(){
        // given: a map where the suicide cabin is at the south-east
        Futuramap map = FuturamapBuilder.fromString("   \n @ \n  $\n###");

        // when: getting directions from bender
        // then: directions contains only one step south
        assertThat(bender.getDirections(map)).isEqualTo(Arrays.asList("SOUTH", "EAST"));
    }

    @Test
    public void keepDirection(){
        // given: Bender has to change direction, meeting an obstacle
        Futuramap map = FuturamapBuilder.fromString( "######\n" +
                                                        "#@  $#\n" +
                                                        "##   #\n" +
                                                        "######");
        // when: getting directions
        // then: Bender keep the new direction after the change, and does not go south as soon as he can
        assertThat(bender.getDirections(map))
                .isEqualTo(Arrays.asList("EAST", "EAST", "EAST"));
    }

    @Test
    public void directionModifier(){
        // given: Bender encounters a direction modifier
        Futuramap map = FuturamapBuilder.fromString( "#####\n" +
                                                        "# @ #\n" +
                                                        "#$W #\n" +
                                                        "#####");
        // when: getting directions
        // then: Bender follow the direction modifier, ignoring normal priorities
        assertThat(bender.getDirections(map))
                .isEqualTo(Arrays.asList("SOUTH", "WEST"));
    }

    @Test
    public void breakerModeOn(){
        // given: Bender encounters a beer
        Futuramap map = FuturamapBuilder.fromString( "######\n" +
                                                        "#@BX$#\n" +
                                                        "######");
        // when: getting directions
        // then: after driking the beer, Bender can pass through breakable obstacles.
        // broken obstacles disappear.
        assertThat(bender.getDirections(map))
                .isEqualTo(Arrays.asList("EAST", "EAST", "EAST"));
        assertThat(map.get(3,1)).isEqualTo(' ');
    }

    @Test
    public void breakerModeOff(){
        // given: Bender encounters a second beer
        Futuramap map = FuturamapBuilder.fromString( "#####\n" +
                                                        "#@  #\n" +
                                                        "#B  #\n" +
                                                        "#X  #\n" +
                                                        "#B  #\n" +
                                                        "#X  #\n" +
                                                        "#  $#\n" +
                                                        "#####");
        // when: getting directions
        // then: the second beer disable the breaker mode and Bender can no longer pass through obstacles
        assertThat(bender.getDirections(map))
                .isEqualTo(Arrays.asList("SOUTH", "SOUTH", "SOUTH", "EAST", "EAST", "SOUTH", "SOUTH"));
    }

    @Test
    public void priorityInverter(){
        // given: Bender encounters a priority inverter
        Futuramap map = FuturamapBuilder.fromString( "#####\n" +
                                                        "# @ #\n" +
                                                        "#$I #\n" +
                                                        "#####");
        // when: Bender will have to change direction
        // then: Bender will use the priorities in reverse order
        assertThat(bender.getDirections(map))
                .isEqualTo(Arrays.asList("SOUTH", "WEST"));
    }

    @Test
    public void teleport(){
        // given: a map with 2 téleporters
        Futuramap map = FuturamapBuilder.fromString( "#####\n" +
                                                        "#@ T#\n" +
                                                        "#   #\n" +
                                                        "#T $#\n" +
                                                        "#####");
        // when: Bender enters a teleporter
        // then: Bender is teleported to the other teleporter and resume his path from there
        assertThat(bender.getDirections(map))
                .isEqualTo(Arrays.asList("SOUTH", "SOUTH", "SOUTH", "SOUTH"));
    }

}
