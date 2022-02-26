package IllegalMoveException;

public class IllegalMoveException extends RuntimeException{
    
    public IllegalMoveException(){
        super("You cannot make that move");
    }

    public IllegalMoveException(String msg){
        super(msg);
    }

    public IllegalMoveException(String msg, Throwable t){
        super(msg, t);
    }

    public IllegalMoveException(Throwable t){
        super(t);
    }

}
