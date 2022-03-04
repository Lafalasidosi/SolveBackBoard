package solve;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import readFile.FileReader;

public class Solve {
    
    public static ArrayList<Move> moves = new ArrayList<Move>(0);
    public static ArrayList<Ply> plies = new ArrayList<Ply>(0);

    public static void main(String[] args) {
        ArrayList<String[]> boards = FileReader.getBoards(new String[]{"data.txt"});
        int[] board;
        int[] diceRolls;
        int[] reverseDiceRolls;

        // take a board from input
        // (translate from Casperson's format to mine)
        // return possible moves
        for(int i = 0; i < boards.size(); i++){
            board = FileReader.extractBoard(boards.get(i));
            diceRolls = FileReader.extractDiceRolls(boards.get(i));
            reverseDiceRolls = new int[]{diceRolls[1], diceRolls[0]};
            if(diceRolls[0] == diceRolls[1]){
                diceRolls = timesTwo(diceRolls);
                reverseDiceRolls = timesTwo(reverseDiceRolls);
            }

            System.out.println();
            displayBoard(board);
            System.out.println("\nLegal moves for this board:");

            solve(board, plies, diceRolls);
            solve(board, plies, reverseDiceRolls);

            prune(moves);

            for(Move m : moves){
                System.out.println(m.toString());
            }

            moves.clear();
        }
    }

    /**
    * Want: First off, the case for returning is if you have no more moves to work with
    * or you reach the end of the board
    * Also, whenever you derecurse, the last ply found should be deleted
    * If an illegal move is encountered, do nothing and continue
    * automatically handles "doubles" since size of rollsLeft is barely mentioned.
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
            if(board[i] < 1)
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
                 } else {
                    // plies.add(new Ply(i+1, i+1-rollsLeft[0]));
                    // moves.add(new Move(plies));
                    // plies.remove(plies.size() - 1);
                    continue;
                 }
                solve(boardCopy, plies, subarray(rollsLeft)); 
            }
       }
       if(plies.size() > 0) // remove last ply whenever method returns
            moves.add(new Move(plies));
            if(plies.size() > 0)
                plies.remove(plies.size() - 1);
            return;
    }

    public static void prune(ArrayList<Move> moves){
        // you must use as many dice as possible
        removeXPlyMoves(moves, maxMoveSize(moves));

        // remove any duplicate entries
        int length = moves.size();
        for(int i = 0; i < length; i++){
            for(int j = i+1; j < length; j++){
                if(moves.get(i).equals(moves.get(j))){
                    moves.remove(j);
                    length--;
                }
            }
        }
    }

    public static void removeXPlyMoves(ArrayList<Move> moves, int size){
        for(int i = 0; i < moves.size(); i++){
            if(moves.get(i).getSize() < size) {
                moves.remove(i);
                i--;
            }
        }
    }

    public static int maxMoveSize(ArrayList<Move> moves){
        int result = 0;
        for(Move m : moves){
            if(m.getSize() == 4){
                return 4;
            }
            result = Math.max(result, m.getSize());
        }
        return result;
    }
    
    /**
     * Ham-fisted way of shortening the rollsLeft array in solve().
     * 
     * @param arr
     * @return
     */
    public static int[] subarray(int[] arr){
        int[] result;
        if(arr.length > 1){
            result = new int[arr.length - 1];
            for(int i = 0; i < arr.length -1; i++)
                result[i] = arr[i+1];
        }
        else{
            result = new int[0];
        }
        return result;
    }
    
    public static void displayBoard(int[] b){
        for(int a : b)
            System.out.printf("%d,", a);
    }

    public static int[] timesTwo(int[] x){
        int[] result = new int[4];
        for(int i = 0; i < 4; i++)
            result[i] = x[0];
        return result;
    }

}
