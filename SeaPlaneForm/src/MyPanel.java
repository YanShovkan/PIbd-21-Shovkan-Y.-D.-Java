import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private SeaPlane seaPlane;

    public void paintComponent(Graphics g) {
        if (seaPlane != null){
            seaPlane.draw(g);
        }
    }

    public SeaPlane getSeaPlane() {
        return seaPlane;
    }

    public void setSeaPlane(SeaPlane seaPlane) {
        this.seaPlane = seaPlane;
    }
}