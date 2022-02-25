import java.util.Arrays;

public class Solve{

    public static void main(String[] args){


        int[] b = new int[24];
        for(int i = 0; i < 24; i++){
            b[i] = 0;
        }
        
        b[6] = 2;
        b[19] = 5;
        b[12] = 9;
        b[11] = 10;

        int[] moves = {3, 1};

        display(b);
        oneSolve(b, 3, 0);
        
    }

    public static void oneSolve(int[] b, int move, int start){
        // if 0, do nothing
        //otherwise, show what would happen if that move were made
        int length = b.length;
        
        for(int i = start; i < length; i++){
            if(b[i] == 0)
                continue;
            else{
                showFuture(b, move, i);
            }
        }
    }

    public  static void showFuture(int[] b, int move, int index){
        int[] a = Arrays.copyOf(b, b.length);
        a[index]--;
        if(index >= move){
            a[index - move]++;
        }
        display(a);
    }

    public static void display(int[] b){
        for(int i : b)
            System.out.printf("%d,", i);
        System.out.println();
    }
}