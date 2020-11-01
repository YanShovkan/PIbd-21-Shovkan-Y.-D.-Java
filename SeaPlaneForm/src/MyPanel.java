import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private IAirTransport plane;

    public void paint(Graphics g) {
        if (plane != null) {
            plane.DrawPlane(g);
        }
    }

    public IAirTransport getPlane() {
        return plane;
    }

    public void setPlane(IAirTransport plane) {
        this.plane = plane;
    }
}