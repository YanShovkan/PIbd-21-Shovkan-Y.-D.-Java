public class AirfieldOverflowException extends Exception {
    AirfieldOverflowException(){
        super("На аэродроме нет свободных мест");
    }
}
