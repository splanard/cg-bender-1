package fr.splanard.codingame.bender.model;

import fr.splanard.codingame.bender.model.builder.FuturamapBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FuturamapTest {

    @Test
    public void oneEmptyRow(){
        // given: one simple empty row (only empty cells)
        String row = "   ";
        Futuramap map = new Futuramap(1, 3);

        // when: adding the new row
        map.newRow(row);

        // then: every cell is empty
        for( int x = 0; x < 3; x++ ){
            assertThat(map.get(x,0)).isEqualTo(' ');
        }
    }

    @Test
    public void everyPossibleCharacters(){
        // given: one line containing every possible character, except bender's position
        String row = "#X$SENWBIT ";
        Futuramap map = new Futuramap(1, row.length());

        // when: adding the new row
        map.newRow(row);

        // then: every character is at the right place
        for( int x = 0; x < row.length(); x++){
            assertThat(map.get(x, 0)).isEqualTo(row.charAt(x));
        }
    }

    @Test
    public void twoEmptyLines(){
        // given: two empty lines
        String row1 = "   ";
        String row2 = "   ";
        Futuramap map = new Futuramap(2, 3);

        // when: adding the 2 rows
        map.newRow(row1);
        map.newRow(row2);

        // then: the corners are empty cells
        assertThat(map.get(0,0)).isEqualTo(' ');
        assertThat(map.get(2, 1)).isEqualTo(' ');
    }

    @Test
    public void bendersInitialPosition(){
        // given: one simple row containing bender's symbol
        String row = "  @  ";
        Futuramap map = new Futuramap(1, 5);

        // when: adding the row, then getting bender's initial position
        map.newRow(row);
        int[] bendersPosition = map.getBendersInitialPosition();

        // then: bender's position is correct, the corresponding cell of the map is empty
        assertThat(bendersPosition).isEqualTo(new int[]{2, 0});
        assertThat(map.get(bendersPosition[0], bendersPosition[1])).isEqualTo(' ');
    }

    @Test
    public void getFromPositionAndDirection(){
        // given: a map with cardinal points
        Futuramap map = FuturamapBuilder.fromString(" N \nW E\n S ");
        int[] position = new int[]{1, 1};

        // when: getting the cell content from position (int array)
        // then: return the right value
        assertThat(map.get(position, "NORTH")).isEqualTo('N');
        assertThat(map.get(position, "SOUTH")).isEqualTo('S');
        assertThat(map.get(position, "EAST")).isEqualTo('E');
        assertThat(map.get(position, "WEST")).isEqualTo('W');
    }

    @Test
    public void getOtherTeleporterPosition(){
        // given: a map with 2 teleporters
        Futuramap map = FuturamapBuilder.fromString("T  \n  T");

        // when: asking for the other teleporter position with the position of a teleporter
        // then: return the position of the other. Works both ways.
        assertThat(map.getOtherTeleporterPosition(new int[]{0,0})).isEqualTo(new int[]{2,1});
        assertThat(map.getOtherTeleporterPosition(new int[]{2,1})).isEqualTo(new int[]{0,0});
    }

}
