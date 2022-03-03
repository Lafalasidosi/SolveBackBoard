package solve;
import java.util.ArrayList;

public class Move{
    
    private ArrayList<Ply> plies;

    public Move(Ply p){
        plies = new ArrayList<Ply>(0);
        plies.add(p);
    }
    
    public Move(Ply ply1, Ply ply2){
        plies = new ArrayList<Ply>(0);
        plies.add(ply1);
        plies.add(ply2);
    }

    public Ply getPly1(){
        return plies.get(0);
    }

    public Ply getPly2(){
        return plies.get(1);
    }
}
