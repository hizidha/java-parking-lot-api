package Exceptions;

public class CarAlreadyParkedException extends RuntimeException {
    public CarAlreadyParkedException(){
        super("Car Already Parked.");
    }
    
    public CarAlreadyParkedException(String message){
        super(message);
    }
}
