package mp.crud.exception;

public class InvalidParameterException extends RuntimeException{
    public InvalidParameterException(){
        super();
    }
    
    public InvalidParameterException(String message){
        super(message);
    }
    
    public InvalidParameterException(String message, Throwable cause){
        super(message, cause);
    }
}
