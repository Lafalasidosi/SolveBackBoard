package solve;
import java.util.ArrayList;
import java.util.Arrays;
import IllegalMoveException.IllegalMoveException;

public class Solve {

    public static void main(String[] args) {

        // start with an 8-point board
        int[] b = new int[8];
        for (int i = 0; i < 8; i++) {
            b[i] = 0;
        }

        // fill test spots
        b[3] = 2;
        b[7] = 7;

        int[] diceRolls = { 2, 1 };

        // test move solver
        displayBoard(b);
        System.out.println();
         
    }

    /**
     * Given a single value from a six-sided die, this method takes a "backgammon
     * board" and determines the possible resultant positions. The technique is
     * outlined as follows: start at the beginning of the board. Move along until
     * you come upon a movable piece. If you can move it, add the resulting board
     * to a running list of possible results. Once all possibilities have been
     * considered, return the list of possible resultant boards.
     * 
     * @param board
     * @param roll
     * @return result return the list of possible resultant boards
     */
    public static ArrayList<String> oneSolve(int[] board, int roll) {
    }

    public static void displayBoard(int[] b){
        for(int a : b){
            System.out.printf("%d,", a);
        }
        System.out.println("\n");

}
