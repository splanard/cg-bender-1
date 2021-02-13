package fr.splanard.codingame.bender;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    private String getOutput() {
        return testOut.toString();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    private void assertOutputIs(String expectedOutput){
        assertThat(getOutput()).isEqualTo(expectedOutput+System.lineSeparator());
    }

    @Test
    public void simpleMouvements() {
        provideInput("5 5\n" +
                "#####\n" +
                "#@  #\n" +
                "#   #\n" +
                "#  $#\n" +
                "#####");
        Solution.main(new String[0]);
        assertOutputIs("SOUTH\nSOUTH\nEAST\nEAST");
    }

    @Test
    public void obstacles(){
        provideInput("8 8\n" +
                "########\n" +
                "# @    #\n" +
                "#     X#\n" +
                "# XXX  #\n" +
                "#   XX #\n" +
                "#   XX #\n" +
                "#     $#\n" +
                "########");
        Solution.main(new String[0]);
        assertOutputIs("SOUTH\nEAST\nEAST\nEAST\nSOUTH\n" +
                "EAST\nSOUTH\nSOUTH\nSOUTH");
    }

    @Test
    public void priorites(){
        provideInput("8 8\n" +
                "########\n" +
                "#     $#\n" +
                "#      #\n" +
                "#      #\n" +
                "#  @   #\n" +
                "#      #\n" +
                "#      #\n" +
                "########");
        Solution.main(new String[0]);
        assertOutputIs("SOUTH\nSOUTH\nEAST\nEAST\n" +
                "EAST\nNORTH\nNORTH\nNORTH\nNORTH\nNORTH");
    }

    @Test
    public void ligneDroite(){
        provideInput("8 8\n" +
                "########\n" +
                "#      #\n" +
                "# @    #\n" +
                "# XX   #\n" +
                "#  XX  #\n" +
                "#   XX #\n" +
                "#     $#\n" +
                "########");
        Solution.main(new String[0]);
        assertOutputIs("EAST\nEAST\nEAST\nEAST\n" +
                "SOUTH\nSOUTH\nSOUTH\nSOUTH");
    }

    @Test
    public void modificateursDeTrajectoire(){
        provideInput("10 10\n" +
                "##########\n" +
                "#        #\n" +
                "#  S   W #\n" +
                "#        #\n" +
                "#  $     #\n" +
                "#        #\n" +
                "#@       #\n" +
                "#        #\n" +
                "#E     N #\n" +
                "##########");
        Solution.main(new String[0]);
        assertOutputIs("SOUTH\nSOUTH\nEAST\nEAST\n" +
                "EAST\nEAST\nEAST\nEAST\nNORTH\nNORTH\nNORTH\n" +
                "NORTH\nNORTH\nNORTH\nWEST\nWEST\nWEST\nWEST\n" +
                "SOUTH\nSOUTH");
    }

    @Test
    public void modeCasseur(){
        provideInput("10 10\n" +
                "##########\n" +
                "# @      #\n" +
                "# B      #\n" +
                "#XXX     #\n" +
                "# B      #\n" +
                "#    BXX$#\n" +
                "#XXXXXXXX#\n" +
                "#        #\n" +
                "#        #\n" +
                "##########");
        Solution.main(new String[0]);
        assertOutputIs("SOUTH\nSOUTH\nSOUTH\nSOUTH\n" +
                "EAST\nEAST\nEAST\nEAST\nEAST\nEAST");
    }

    @Test
    public void inverseur(){
        provideInput("10 10\n" +
                "##########\n" +
                "#    I   #\n" +
                "#        #\n" +
                "#       $#\n" +
                "#       @#\n" +
                "#        #\n" +
                "#       I#\n" +
                "#        #\n" +
                "#        #\n" +
                "##########");
        Solution.main(new String[0]);
        assertOutputIs("SOUTH\nSOUTH\nSOUTH\nSOUTH\n" +
                "WEST\nWEST\nWEST\nWEST\nWEST\nWEST\nWEST\nNORTH\n" +
                "NORTH\nNORTH\nNORTH\nNORTH\nNORTH\nNORTH\nEAST\n" +
                "EAST\nEAST\nEAST\nEAST\nEAST\nEAST\nSOUTH\nSOUTH");
    }

    @Test
    public void teleportation(){
        provideInput("10 10\n" +
                "##########\n" +
                "#    T   #\n" +
                "#        #\n" +
                "#        #\n" +
                "#        #\n" +
                "#@       #\n" +
                "#        #\n" +
                "#        #\n" +
                "#    T  $#\n" +
                "##########");
        Solution.main(new String[0]);
        assertOutputIs("SOUTH\nSOUTH\nSOUTH\nEAST\n" +
                "EAST\nEAST\nEAST\nEAST\nEAST\nEAST\nSOUTH\nSOUTH\n" +
                "SOUTH\nSOUTH\nSOUTH\nSOUTH\nSOUTH");
    }

    @Test
    public void murCasse(){
        provideInput("10 10\n" +
                "##########\n" +
                "#        #\n" +
                "#  @     #\n" +
                "#  B     #\n" +
                "#  S   W #\n" +
                "# XXX    #\n" +
                "#  B   N #\n" +
                "# XXXXXXX#\n" +
                "#       $#\n" +
                "##########");
        Solution.main(new String[0]);
        assertOutputIs("SOUTH\nSOUTH\nSOUTH\nSOUTH\n" +
                "EAST\nEAST\nEAST\nEAST\nNORTH\nNORTH\nWEST\nWEST\n" +
                "WEST\nWEST\nSOUTH\nSOUTH\nSOUTH\nSOUTH\nEAST\n" +
                "EAST\nEAST\nEAST\nEAST");
    }

    @Test
    public void allTogether(){
        provideInput("15 15\n" +
                "###############\n" +
                "#      IXXXXX #\n" +
                "#  @          #\n" +
                "#             #\n" +
                "#             #\n" +
                "#  I          #\n" +
                "#  B          #\n" +
                "#  B   S     W#\n" +
                "#  B   T      #\n" +
                "#             #\n" +
                "#         T   #\n" +
                "#         B   #\n" +
                "#            $#\n" +
                "#        XXXX #\n" +
                "###############");
        Solution.main(new String[0]);
        assertOutputIs("SOUTH\nSOUTH\nSOUTH\nSOUTH\nSOUTH\nSOUTH\n" +
                "SOUTH\nSOUTH\nSOUTH\nSOUTH\nSOUTH\nWEST\nWEST\nNORTH\nNORTH\n" +
                "NORTH\nNORTH\nNORTH\nNORTH\nNORTH\nNORTH\nNORTH\nNORTH\nNORTH\n" +
                "NORTH\nEAST\nEAST\nEAST\nEAST\nEAST\nEAST\nEAST\nEAST\nEAST\n" +
                "EAST\nEAST\nEAST\nSOUTH\nSOUTH\nSOUTH\nSOUTH\nSOUTH\nSOUTH\n" +
                "WEST\nWEST\nWEST\nWEST\nWEST\nWEST\nSOUTH\nSOUTH\nSOUTH\nEAST\n" +
                "EAST\nEAST");
    }

    @Test
    public void loop(){
        provideInput("15 15\n" +
                "###############\n" +
                "#      IXXXXX #\n" +
                "#  @          #\n" +
                "#E S          #\n" +
                "#             #\n" +
                "#  I          #\n" +
                "#  B          #\n" +
                "#  B   S     W#\n" +
                "#  B   T      #\n" +
                "#             #\n" +
                "#         T   #\n" +
                "#         B   #\n" +
                "#N          W$#\n" +
                "#        XXXX #\n" +
                "###############");
        Solution.main(new String[0]);
        assertOutputIs("LOOP");
    }

    @Test
    public void bouclesMultiples(){
        provideInput("30 15\n" +
                "###############\n" +
                "#  #@#I  T$#  #\n" +
                "#  #    IB #  #\n" +
                "#  #     W #  #\n" +
                "#  #      ##  #\n" +
                "#  #B XBN# #  #\n" +
                "#  ##      #  #\n" +
                "#  #       #  #\n" +
                "#  #     W #  #\n" +
                "#  #      ##  #\n" +
                "#  #B XBN# #  #\n" +
                "#  ##      #  #\n" +
                "#  #       #  #\n" +
                "#  #     W #  #\n" +
                "#  #      ##  #\n" +
                "#  #B XBN# #  #\n" +
                "#  ##      #  #\n" +
                "#  #       #  #\n" +
                "#  #       #  #\n" +
                "#  #      ##  #\n" +
                "#  #  XBIT #  #\n" +
                "#  #########  #\n" +
                "#             #\n" +
                "# ##### ##### #\n" +
                "# #     #     #\n" +
                "# #     #  ## #\n" +
                "# #     #   # #\n" +
                "# ##### ##### #\n" +
                "#             #\n" +
                "###############");
        Solution.main(new String[0]);
        assertOutputIs("SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "NORTH\n" +
                "WEST\n" +
                "WEST\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "SOUTH\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST\n" +
                "EAST");
    }
}
