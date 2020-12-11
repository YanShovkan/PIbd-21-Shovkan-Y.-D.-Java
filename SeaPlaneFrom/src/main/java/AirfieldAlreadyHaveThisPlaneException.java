public class AirfieldAlreadyHaveThisPlaneException extends Exception {
    public AirfieldAlreadyHaveThisPlaneException() {
        super("На аэродроме уже есть такаой самолёт");
    }
}
