package fr.splanard.codingame.bender.model.builder;

import fr.splanard.codingame.bender.model.Futuramap;

public class FuturamapBuilder {

    public static Futuramap fromString(String s) {
        String[] rows = s.split("\n");
        Futuramap map = new Futuramap(rows.length, rows[0].length());
        for(String row : rows){
            map.newRow(row);
        }
        return map;
    }
}
