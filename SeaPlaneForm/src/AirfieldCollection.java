import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AirfieldCollection {

    private final Map<String, Airfield<Plane, IFloatForm>> airfieldStages;
    private final int frameWidth;
    private final int frameHeight;
    private final String separator = ":";

    public AirfieldCollection(int frameWidth, int frameHeight) {
        airfieldStages = new HashMap<>();
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }

    public void addAirfield(String name) {
        if (!airfieldStages.containsKey(name)) {
            airfieldStages.put(name, new Airfield<>(frameWidth, frameHeight));
        }
    }

    public void delAirfield(String name) {
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

    public boolean saveAllData(String filename) throws IOException {

        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write("AirfieldCollection\n");
        for (String level : airfieldStages.keySet()) {

            fileWriter.write("Airfield" + separator + level + "\n");
            IAirTransport plane = null;

            for (int i = 0; (plane = airfieldStages.get(level).getAirPlane(i)) != null; i++) {
                if (plane.getClass().toString().equals("class Plane")) {
                    fileWriter.write("Plane" + separator);
                }
                if (plane.getClass().toString().equals("class SeaPlane")) {
                    fileWriter.write("SeaPlane" + separator);
                }
                fileWriter.write(plane.toString() + "\n");
            }

        }

        fileWriter.close();
        return true;
    }

    public boolean saveChosenAirfieldData(String filename, String name) throws IOException {

        if (!airfieldStages.containsKey(name)) {
            return false;
        }
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write("AirfieldCollection\n");
        Airfield<Plane, IFloatForm> airfield = airfieldStages.get(name);
        fileWriter.write("Airfield" + separator + name + "\n");
        IAirTransport plane = null;

        for (int i = 0; (plane = airfield.getAirPlane(i)) != null; i++) {
            if (plane.getClass().toString().equals("class Plane")) {
                fileWriter.write("Plane" + separator);
            }
            if (plane.getClass().toString().equals("class SeaPlane")) {
                fileWriter.write("SeaPlane" + separator);
            }
            fileWriter.write(plane.toString() + "\n");
        }
        fileWriter.close();
        return true;
    }

    public boolean loadChosenAirfieldData(String filename) throws IOException {

        FileReader fileReader = new FileReader(filename);
        Scanner scanner = new Scanner(fileReader);
        String line = scanner.nextLine();
        String key = "";
        Plane plane = null;

        if (line.contains("AirfieldCollection")) {

            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
            } else {
                line = null;
            }

            while (line != null) {
                if (line.contains("Airfield")) {
                    key = line.split(separator)[1];
                    if (airfieldStages.containsKey(key)) {
                        airfieldStages.get(key).deleteAllPlanes();
                    } else {
                        airfieldStages.put(key, new Airfield<Plane, IFloatForm>(frameWidth, frameHeight));
                    }
                    if (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                    } else {
                        break;
                    }
                    continue;
                }
                if (line.split(separator)[0].equals("Plane")) {
                    plane = new Plane(line.split(separator)[1]);
                } else if (line.split(separator)[0].equals("SeaPlane")) {
                    plane = new SeaPlane(line.split(separator)[1]);
                }
                var result = airfieldStages.get(key).plus(plane);
                if (!result) {
                    return false;
                }
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                } else {
                    break;
                }
            }
            fileReader.close();
            return true;
        }
        fileReader.close();
        return false;
    }

    public boolean loadAllData(String filename) throws IOException {

        FileReader fileReader = new FileReader(filename);
        Scanner scanner = new Scanner(fileReader);

        String line = scanner.nextLine();
        String key = "";
        Plane plane = null;

        if (line.contains("AirfieldCollection")) {
            airfieldStages.clear();
            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
            } else {
                line = null;
            }
            while (line != null) {
                if (line.contains("Airfield")) {
                    key = line.split(separator)[1];
                    airfieldStages.put(key, new Airfield<Plane, IFloatForm>(frameWidth, frameHeight));
                    if (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                    } else {
                        break;
                    }
                    continue;
                }
                if (line.split(separator)[0].equals("Plane")) {
                    plane = new Plane(line.split(separator)[1]);
                } else if (line.split(separator)[0].equals("SeaPlane")) {
                    plane = new SeaPlane(line.split(separator)[1]);
                }
                var result = airfieldStages.get(key).plus(plane);
                if (!result) {
                    return false;
                }
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                } else {
                    break;
                }
            }
            fileReader.close();
            return true;
        }
        fileReader.close();
        return false;
    }
}