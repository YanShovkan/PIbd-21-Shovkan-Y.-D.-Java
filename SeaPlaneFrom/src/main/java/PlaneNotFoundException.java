public class PlaneNotFoundException extends Exception {
    public PlaneNotFoundException(int i) {
        super("Не найден самолёт по месту " + i);
    }
}
