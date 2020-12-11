import java.util.Comparator;

public class PlaneComparer implements Comparator<IAirTransport> {

    public int compare(IAirTransport x, IAirTransport y) {
        if (x instanceof SeaPlane && y instanceof SeaPlane) {
            return comparerSeaPlane((SeaPlane) x, (SeaPlane) y);
        }
        if (x instanceof SeaPlane && y instanceof Plane) {
            return 1;
        }
        if (x instanceof Plane && y instanceof SeaPlane) {
            return -1;
        }
        if (x instanceof Plane && y instanceof Plane) {
            return comparerPlane((Plane) x, (Plane) y);
        }
        return 0;
    }

    private int comparerPlane(Plane x, Plane y) {
        if (x.maxSpeed != y.maxSpeed) {
            return x.maxSpeed - y.maxSpeed;
        }
        if (x.weight != y.weight) {
            return x.weight - y.weight;
        }
        if (x.mainColor != y.mainColor) {
            return x.mainColor.getRGB() - y.mainColor.getRGB();
        }
        return 0;
    }

    private int comparerSeaPlane(SeaPlane x, SeaPlane y) {
        var res = comparerPlane(x, y);
        if (res != 0) {
            return res;
        }
        if (x.getDopColor() != y.getDopColor()) {
            return x.getDopColor().getRGB() - y.getDopColor().getRGB();
        }
        if (x.isLowerWing() != y.isLowerWing()) {
            if (x.isLowerWing()) {
                return 1;
            }
            return -1;
        }
        if (x.isPlaneFloat() != y.isPlaneFloat()) {
            if (x.isPlaneFloat()) {
                return 1;
            }
            return -1;
        }
        if (x.getFloatFormStr() != y.getFloatFormStr()) {
            int firstPlaneFloatID = 0;
            int secondPlaneFloatID = 0;
            switch (x.getFloatFormStr()) {
                case "Прямоугольный":
                    firstPlaneFloatID = 1;
                    break;
                case "Овальный":
                    firstPlaneFloatID = 2;
                    break;
                case "Комбинированый":
                    firstPlaneFloatID = 3;
                    break;
            }
            switch (y.getFloatFormStr()) {
                case "Прямоугольный":
                    secondPlaneFloatID = 1;
                    break;
                case "Овальный":
                    secondPlaneFloatID = 2;
                    break;
                case "Комбинированый":
                    secondPlaneFloatID = 3;
                    break;
            }
            return firstPlaneFloatID - secondPlaneFloatID;
        }
        if (x.getFloats() != y.getFloats()) {
            return x.getFloats() - y.getFloats();
        }
        return 0;
    }
}
