package solve;
import java.util.ArrayList;
import java.util.Arrays;
import IllegalMoveException.IllegalMoveException;

public class Solve {
    
    public static ArrayList<Move> moves = new ArrayList<Move>(0);
    public static ArrayList<Ply> plies = new ArrayList<Ply>(0);

    public static void main(String[] args) {

        ArrayList<Ply> plies = new ArrayList<Ply>(0);

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

        solve(b, plies, diceRolls);

         for(Move m : moves)
             System.out.println(m.toString());
         
    }

    /**
    * Want: a recursive method to solve a backgammon board
    * First off, the case for returning is if you have no more moves to work with
    */
    public static void solve(int[] board, ArrayList<Ply> plies, int[] rollsLeft){
        //
        if(rollsLeft.length == 0){
            moves.add(new Move(plies));
            if(plies.size() > 0)
                plies.remove(plies.size() - 1);
            return;
        }
       int l = board.length;
       int[] boardCopy = Arrays.copyOf(board, board.length);
       for(int i = 0; i < l; i++){
            if(board[i] == 0)
                continue;
            else{
                int moveTo = boardCopy[i - rollsLeft[0]];
                if (i >= rollsLeft[0] && moveTo > -2) {
                    boardCopy[i]--;
                    plies.add(new Ply(i+1, i+1-rollsLeft[0]));
                    if (moveTo > -1)
                        boardCopy[i - rollsLeft[0]]++;
                    else
                        boardCopy[i - rollsLeft[0]] += 2;
                 } //else {
                //     throw new IllegalMoveException(); // might have to leave this out
                // }
                solve(boardCopy, plies, subarray(rollsLeft)); 
            }
       }
    }
    
    public static int[] subarray(int[] arr){
        int[] result;
        if(arr.length == 2){
            result = new int[1];
            result[0] = arr[1];
        }
        else{
            result = new int[0];
        }
        return result;
    }
    
    public static void displayBoard(int[] b){
        for(int a : b)
            System.out.printf("%d,", a);

        System.out.println("\n");
    }

}
