package fr.splanard.codingame.bender.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Futuramap {

    private final int columns;
    private final int rows;

    private int fillRow = 0;
    private final char[][] data;

    private int[] bendersInitialPosition;
    private List<int[]> teleportersPositions = new ArrayList<>();

    public Futuramap(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        data = new char[columns][rows];
        bendersInitialPosition = new int[]{-1, -1};
    }

    public void newRow(String row) {
        for(int x = 0; x < columns; x++){
            char c = row.charAt(x);
            if( c == '@' ){
                bendersInitialPosition = new int[]{x, fillRow};
                c = ' ';
            }
            else if( c == 'T' ){
                teleportersPositions.add(new int[]{x, fillRow});
            }
            data[x][fillRow] = c;
        }
        fillRow++;
    }

    public char get(int x, int y){
        validatePosition(x, y);
        return data[x][y];
    }

    public char get(int[] position){
        return get(position[0],position[1]);
    }

    public char get(int[] position, String direction){
        return get(nextPosition(position, direction));
    }

    public int[] getBendersInitialPosition() {
        return bendersInitialPosition;
    }

    public int[] nextPosition(int[] position, String direction){
        validatePosition(position[0], position[1]);
        switch (direction) {
            case "EAST":
                return new int[]{position[0] + 1, position[1]};
            case "NORTH":
                return new int[]{position[0], position[1] - 1};
            case "SOUTH":
                return new int[]{position[0], position[1] + 1};
            case "WEST":
                return new int[]{position[0] - 1, position[1]};
            default:
                throw new IllegalArgumentException("Unknown direction: " + direction);
        }
    }

    public void set(int x, int y, char content){
        validatePosition(x, y);
        data[x][y] = content;
    }

    private void validatePosition(int x, int y){
        if( x < 0 || x >= columns || y < 0 || y >= rows ){
            throw new IllegalArgumentException("Invalid position: "+x+"."+y);
        }
    }

    public int[] getOtherTeleporterPosition(int[] position) {
        if( teleportersPositions.size() > 0 ){
            return Arrays.equals(teleportersPositions.get(0), position)
                    ? teleportersPositions.get(1)
                    : teleportersPositions.get(0);
        }
        return new int[]{-1,-1};
    }
}
