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
        int[] reverseDiceRolls = {1,2};

        // test move solver
        displayBoard(b);
        System.out.println();

        solve(b, plies, diceRolls);
        solve(b, plies, reverseDiceRolls);

        prune(moves);

         for(Move m : moves)
             System.out.println(m.toString());
         
    }

    /**
    * Want: First off, the case for returning is if you have no more moves to work with
    * or you reach the end of the board
    * Also, whenever you derecurse, the last ply found should be deleted
    * If an illegal move is encountered, do nothing and continue
    */
    public static void solve(int[] board, ArrayList<Ply> plies, int[] rollsLeft){
        // "base case"
        if(rollsLeft.length == 0){
            moves.add(new Move(plies));
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
       if(plies.size() > 0) // remove last ply whenever method returns
           plies.remove(plies.size() - 1);
    }

    public static void prune(ArrayList<Move> moves){
        if(contains2PlyMove(moves)){
            remove1PlyMoves(moves);
        }

        int length = moves.size();
        for(int i = 0; i < length; i++){
            for(int j = i; j < length; j++){
                if(moves.get(i).equals(moves.get(j))){
                    moves.remove(j);
                    length--;
                }
            }
        }
    }

    public static boolean contains2PlyMove(ArrayList<Move> moves){
        for(Move m : moves){
            if(m.getSize() == 2)
                return true;
        }
        return false;
    }

    public static void remove1PlyMoves(ArrayList<Move> moves){
        for(Move m : moves){
            if(m.getSize() == 1)
                moves.remove(m);
        }
    }
    
    /**
     * Ham-fisted way of shortening the rollsLeft array in solve().
     * 
     * @param arr
     * @return
     */
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
