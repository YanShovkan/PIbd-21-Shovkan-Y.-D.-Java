import javax.swing.*;
import java.awt.*;

public class AirfieldPanel extends JPanel {
    private final AirfieldCollection airfieldCollection;
    private String selectedItem = null;

    @Override
    public void paint(Graphics g) {
        if (selectedItem != null) {
            if (airfieldCollection != null) {
                airfieldCollection.get(selectedItem).Draw(g);
            }
        }
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public AirfieldPanel(AirfieldCollection airfieldCollection) {
        this.airfieldCollection = airfieldCollection;
    }
}
