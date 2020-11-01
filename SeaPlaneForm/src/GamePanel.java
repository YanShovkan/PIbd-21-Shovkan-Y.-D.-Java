import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private IAirTransport plane;

    public void paint(Graphics g) {
        super.paint(g);
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