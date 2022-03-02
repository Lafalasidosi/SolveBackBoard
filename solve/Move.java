package solve;

public class Move{

    private String move;

    /**
    * Assumes valid notation is passed.
    */
    public Move(String move){
        this.move = move;
    }

    public String getMove(){
        return move;
    }
    
    public int getStartPoint(){
        return Integer.parseInt(move.split("/")[0]);
    }

    public int getEndPoint(){
        return Integer.parseInt(move.split("/")[1]);
    }
}
