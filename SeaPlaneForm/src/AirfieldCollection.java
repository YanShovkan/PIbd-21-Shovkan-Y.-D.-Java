import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AirfieldCollection {

    private final Map<String, Airfield<Plane, IFloatForm>> airfieldStages;
    private final int frameWidth;
    private final int frameHeight;

    public AirfieldCollection(int frameWidth, int frameHeight) {
        airfieldStages = new HashMap<>();
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }

    public void AddAirfield(String name) {
        if (!airfieldStages.containsKey(name)) {
            airfieldStages.put(name, new Airfield<>(frameWidth, frameHeight));
        }
    }

    public void DelAirfield(String name) {
        airfieldStages.remove(name);
    }

    public Airfield<Plane, IFloatForm> get(String name) {
        if (airfieldStages.containsKey(name)) {
            return airfieldStages.get(name);
        }
        return null;
    }

    public AirPlane getPlane(String airfieldName, int planeIndex) {
        if (airfieldStages.containsKey(airfieldName)) {
            return airfieldStages.get(airfieldName).getAirPlane(planeIndex);
        }
        return null;
    }

    public Set<String> keySet() {
        return airfieldStages.keySet();
    }

}