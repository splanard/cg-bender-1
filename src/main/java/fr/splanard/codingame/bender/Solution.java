package fr.splanard.codingame.bender;
import fr.splanard.codingame.bender.model.Bender;
import fr.splanard.codingame.bender.model.Futuramap;
import fr.splanard.codingame.bender.model.LoopException;

import java.util.List;
import java.util.Scanner;

/**
 * CodinGame practice - Bender - Episode 1
 * @see https://www.codingame.com/ide/puzzle/bender-episode-1
 **/
class Solution {

    public static final String BEER = "B";
    public static final String BENDER = "@";

    public static final String EAST = "EAST";
    public static final String NORTH = "NORTH";
    public static final String SOUTH = "SOUTH";
    public static final String WEST = "WEST";

    public static final String DIRECTIONS_INVERTER = "I";
    public static final String DIRECTION_MODIFIER_SOUTH = "S";
    public static final String DIRECTION_MODIFIER_EAST = "E";
    public static final String DIRECTION_MODIFIER_NORTH = "N";
    public static final String DIRECTION_MODIFIER_WEST = "W";
    public static final String OBSTACLE_BREAKABLE = "X";
    public static final String OBSTACLE_UNBREAKABLE = "#";
    public static final String SUICIDE_CABIN = "$";
    public static final String TELEPORTER = "T";

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // number of rows on the map
        int C = in.nextInt(); // number of columns on the map
        if (in.hasNextLine()) {
            in.nextLine();
        }

        Futuramap map = new Futuramap(R, C);
        for (int i = 0; i < R; i++) {
            String row = in.nextLine();
            map.newRow( row );
        }

        Bender bender = new Bender();
        List<String> directions = null;
        try {
            directions = bender.getDirections(map);
            System.out.println(String.join("\n", directions));
        } catch (LoopException e) {
            System.out.println("LOOP");
        }
    }
}