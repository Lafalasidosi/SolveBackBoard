package solve;
import java.util.ArrayList;
import java.util.Arrays;
import IllegalMoveException.IllegalMoveException;

public class Solve {

    public static void main(String[] args) {

        int[] b = new int[24];
        for (int i = 0; i < 24; i++) {
            b[i] = 0;
        }

        b[6] = 2;
        b[2] = -2;
        b[19] = 7;

        int[] moves = { 4, 1 };

        display(b);
        System.out.println();
        ArrayList<int[]> a = oneSolve(b, 4);
        for (int[] c : a) {
            display(c);
        }
        // ArrayList<int[]> c = twoSolve(b, moves);
        // for (int[] d : c) {
        // display(d);
        // }
    }

    /**
     * Given a single value from a six-sided die, this method takes a "backgammon
     * board" and determines the possible resultant positions. The technique is
     * outlined as follows: start at the beginning of the board. Move along until
     * you come upon a movable piece. If you can move it, add the resulting board
     * to a running list of possible results. Once all possibilities have been
     * considered, return the list of possible resultant boards.
     * 
     * @param b
     * @param move
     * @param start
     * @return result return the list of possible resultant boards
     */
    public static ArrayList<int[]> oneSolve(int[] b, int move) {
        // if 0, do nothing
        // otherwise, show what would happen if that move were made
        ArrayList<int[]> result = new ArrayList<int[]>(0);
        for (int i = 0; i < 24; i++) {
            if (b[i] <= 0)
                continue;
            else {
                try{
                    result.add(showFuture(b, move, i));
                } catch(IllegalMoveException e){}
            }
        }
        return result;
    }

    /**
     * The inclusion of a second die just means extra steps.
     * To reflect whether both dice can be used, we need to solve
     * 
     * @param b
     * @param moves
     * @return
     */
    // public static ArrayList<int[]> twoSolve(int[] b, int[] moves) {

    // }

    public static void addArrayList(ArrayList<int[]> input, ArrayList<int[]> toBeAdded) {
        for (int[] a : toBeAdded)
            input.add(a);
    }

    public static ArrayList<int[]> intersect(ArrayList<int[]> a, ArrayList<int[]> b) {
        // look for elements common to both
        ArrayList<int[]> result = new ArrayList<int[]>(0);
        for (int[] array : a) {
            if (b.contains(array))
                result.add(array);
        }
        return result;
    }

    public static int[] showFuture(int[] b, int move, int index) {
        int[] a = Arrays.copyOf(b, b.length);
        int moveTo = a[index - move];
        if (index >= move && moveTo > -2) {
            a[index]--;
            if (moveTo > -1)
                a[index - move]++;
            else
                a[index - move] += 2;
        } else {
            throw new IllegalMoveException();
        }
        
        return a;
    }

    public static void display(int[] b) {
        for (int i : b)
            System.out.printf("%d,", i);
        System.out.println();
    }

    public static void prune(ArrayList<int[]> a) {
        int length = a.size();
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (areEqual(a.get(i), (a.get(j)))) {
                    a.remove(j);
                    length--;
                }
            }
        }
    }

    public static boolean areEqual(int[] a, int[] b) {
        int l = a.length;
        for (int i = 0; i < l; i++) {
            if (a[i] != b[i])
                return false;
        }

        return true;
    }
}