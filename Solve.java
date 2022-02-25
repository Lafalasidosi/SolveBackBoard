import java.util.ArrayList;
import java.util.Arrays;

public class Solve {

    public static void main(String[] args) {

        int[] b = new int[24];
        for (int i = 0; i < 24; i++) {
            b[i] = 0;
        }

        b[6] = 2;
        b[19] = 5;
        b[12] = 9;

        int[] moves = { 3, 1 };

        display(b);
        System.out.println();
        // ArrayList<int[]> a = oneSolve(b, 3, 0);
        // for(int[] c : a){
        // display(c);
        // }
        ArrayList<int[]> c = twoSolve(b, moves);
        for (int[] d : c) {
            display(d);
        }
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
     * @return result     return the list of possible resultant boards
     */
    public static ArrayList<int[]> oneSolve(int[] b, int move) {
        // if 0, do nothing
        // otherwise, show what would happen if that move were made
        ArrayList<int[]> result = new ArrayList<int[]>(0);
        for (int i = 0; i < 24; i++) {
            if (b[i] == 0)
                continue;
            else {
                result.add(showFuture(b, move, i));
            }
        }
        return result;
    }

    /**
     * The inclusion of a second die just means extra steps. 
     * 
     * @param b
     * @param moves
     * @return
     */
    public static ArrayList<int[]> twoSolve(int[] b, int[] moves) {
        ArrayList<int[]> result = new ArrayList<int[]>(0);
        ArrayList<int[]> firstLayer = oneSolve(b, moves[0]);
        ArrayList<int[]> otherFirstLayer = oneSolve(b, moves[1]);
        // addArrayList(result, firstLayer); // uncommenting this line results in
        // one-moves being included
        for (int[] a : firstLayer) {
            ArrayList<int[]> nextSetOfSolves = oneSolve(a, moves[1]);
            addArrayList(result, nextSetOfSolves);
        }
        for (int[] a : otherFirstLayer) {
            ArrayList<int[]> nextSetOfSolves = oneSolve(a, moves[0]);
            addArrayList(result, nextSetOfSolves);
        }
        prune(result);
        return result;
    }

    public static void addArrayList(ArrayList<int[]> input, ArrayList<int[]> toBeAdded) {
        for (int[] a : toBeAdded)
            input.add(a);
    }

    public static int[] showFuture(int[] b, int move, int index) {
        int[] a = Arrays.copyOf(b, b.length);
        a[index]--;
        if (index >= move) {
            a[index - move]++;
        }
        return a;
    }

    public static void display(int[] b) {
        for (int i : b)
            System.out.printf("%d,", i);
        System.out.println();
    }

    public static void prune(ArrayList<int[]> a){
        int length = a.size();
        for(int i = 0; i < length - 1; i++){
            for(int j = i+1; j < length; j++){
                if(areEqual(a.get(i),(a.get(j)))){
                    a.remove(j);
                    length--;
                }
            }
        }
    }

    public static boolean areEqual(int[] a, int[] b){
        int l = a.length;
        for(int i = 0; i < l; i++){
            if(a[i] != b[i])
                return false;
        }

        return true;
    }
}