package solve;
import java.util.ArrayList;
import java.util.Arrays;
import IllegalMoveException.IllegalMoveException;

public class Solve {

    public static void main(String[] args) {

        int[] b = new int[8];
        for (int i = 0; i < 8; i++) {
            b[i] = 0;
        }

        b[2] = -2;
        b[3] = 2;
        b[7] = 7;
        //b[5] = -2;

        int[] moves = { 2, 1 };

        display(b);
        System.out.println();
        ArrayList<String> a = oneSolve(b, moves[0]);
        System.out.println("final results: ");
        displayArrayList(a);
         
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
     * @return result return the list of possible resultant boards
     */
    public static ArrayList<String> oneSolve(int[] b, int move) {
        // if 0, do nothing
        // otherwise, show what would happen if that move were made
        ArrayList<String> result = new ArrayList<String>(0);
        for (int i = 0; i < 8; i++) {
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
    public static ArrayList<String> twoSolve(int[] b, int[] moves) {
        ArrayList<String> result = new ArrayList<String>(0);
        ArrayList<String> firstChoice = new ArrayList<String>(0);
        ArrayList<String> secondChoice = new ArrayList<String>(0);
        ArrayList<String> firstChoiceResult = new ArrayList<String>(0);
        ArrayList<String> secondChoiceResult = new ArrayList<String>(0);
        firstChoice = oneSolve(b, moves[0]);
        //System.out.println("results of choosing first die first:");
        //displayArrayList(firstChoice);
        //secondChoice = oneSolve(b, moves[1]);
        //System.out.println("results of choosing second die: first");
        //displayArrayList(secondChoice);
        System.out.println("\n analyzing results of next choices...");
        //for(String a : firstChoice){
            //addArrayList(firstChoiceResult, oneSolve(a, moves[1]));
        //}
        //displayArrayList(firstChoiceResult);
        //for(String a : secondChoice){
            //addArrayList(secondChoiceResult, oneSolve(a, moves[0]));
        //}
        //displayArrayList(secondChoiceResult);
        //System.out.println("\n results common to both choices:");
        ArrayList<String> intersection = intersect(secondChoiceResult, firstChoiceResult);
        //displayArrayList(intersection);
        addArrayList(result, firstChoiceResult);
        addArrayList(result, secondChoice);
        return intersection.size() > 0 ? intersection : result;
    }

    public static void addArrayList(ArrayList<String> input, ArrayList<String> toBeAdded) {
        for (String a : toBeAdded)
            input.add(a);
    }

    public static ArrayList<String> intersect(ArrayList<String> a, ArrayList<String> b) {
        // look for elements common to both
        ArrayList<String> result = new ArrayList<String>(0);
        int aSize, bSize;
        aSize = a.size();
        bSize = b.size();
        for(int i = 0; i < aSize; i++){
            for(int j = 0; j < bSize; j++){
                if(areEqual(a.get(i), b.get(j))){
                    result.add(a.get(i));
                    break;
                }
            }
        }
        return result;
    }

    public static String showFuture(int[] b, int move, int index) {
       // int[] a = Arrays.copyOf(b, b.length);
        String result;
        int moveTo = b[index - move];
        if (index >= move && moveTo > -2) {
            return String.format("%d/%d", ++index, ++moveTo);
        } else {
            throw new IllegalMoveException();
        }
    }

    public static void displayArrayList(ArrayList<String> arr){
        for(String a : arr){
            displayString(a);
        }
        System.out.println();
    }

    public static void display(int[] b) {
        for (int i : b)
            System.out.printf("%d,", i);
        System.out.println();
    }

    public static void displayString(String s){
        System.out.printf("%s,", s);
    }

    public static void prune(ArrayList<String> a) {
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

    public static boolean areEqual(String a, String b) {
        int l = a.length();
        for (int i = 0; i < l; i++) {
            if (a.charAt(i) != b.charAt(i))
                return false;
        }

        return true;
    }
}
