import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Solve{

    public static void main(String[] args){


        int[] b = new int[24];
        for(int i = 0; i < 24; i++){
            b[i] = 0;
        }
        
        b[6] = 2;
        b[19] = 5;

        int[] moves = {3, 1};

        display(b);
        System.out.println();
        // ArrayList<int[]> a = oneSolve(b, 3, 0);
        // for(int[] c : a){
        //     display(c);
        // }
        ArrayList<int[]> c = twoSolve(b, moves);
        for(int[] d : c){
            display(d);
        }
    }

    public static ArrayList<int[]> oneSolve(int[] b, int move, int start){
        // if 0, do nothing
        //otherwise, show what would happen if that move were made
        ArrayList<int[]> result = new ArrayList<int[]>(0);
        for(int i = start; i < 24; i++){
            if(b[i] == 0)
                continue;
            else{
                result.add(showFuture(b, move, i));
            }
        }
        return result;
    }

    public static ArrayList<int[]> twoSolve(int[] b, int[] moves){
        ArrayList<int[]> result = new ArrayList<int[]>(0);
        ArrayList<int[]> firstLayer = oneSolve(b, moves[0], 0);
        addArrayList(result, firstLayer);
        for(int[] a : firstLayer){
            ArrayList<int[]> nextSetOfSolves = oneSolve(a, moves[1], 0);
            addArrayList(result, nextSetOfSolves);
        }
        return result;
    }

    public static void addArrayList(ArrayList<int[]> input, ArrayList<int[]> toBeAdded){
        for(int[] a : toBeAdded)
            input.add(a);
    }

    public static int[] showFuture(int[] b, int move, int index){
        int[] a = Arrays.copyOf(b, b.length);
        a[index]--;
        if(index >= move){
            a[index - move]++;
        }
        return a;
    }

    public static void display(int[] b){
        for(int i : b)
            System.out.printf("%d,", i);
        System.out.println();
    }
}