import javax.swing.*;
import java.awt.*;

public class AirfieldPanel extends JPanel {
    private final Airfield<Plane, IFloatForm> airfield;

    public void paint(Graphics g) {
        if (airfield != null) {
            airfield.Draw(g);
        }
    }

    public AirfieldPanel(Airfield<Plane, IFloatForm> airfield) {
        this.airfield = airfield;
    }
}
