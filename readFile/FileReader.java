package readFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader{

    public static ArrayList<String[]> getBoards(String[] args){
        ArrayList<String[]> results = new ArrayList<String[]>(0);

        try(Scanner input = new Scanner(Paths.get("data.txt"))){
            while(input.hasNext()){
                results.add(input.nextLine().replaceAll("[\\s+]", "").replace(';', ',').split(","));
            }
        } catch(IOException e){
            System.err.println("Done goofed.");
            System.exit(1);
        }

        return results;
    }

    public static int[] extractBoard(String[] input){
        
        int[] result = new int[24];
        for(int i = 2; i < 26; i++)
            result[i-2] = Integer.parseInt(input[i]);
       
        return result;
    }

    public static int[] extractDiceRolls(String[] input){
        return new int[]{Integer.parseInt(input[26]), Integer.parseInt(input[27])};
    }
}