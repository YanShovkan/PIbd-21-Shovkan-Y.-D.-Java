import javax.swing.*;
import java.awt.*;

public class FarmeAirfield {

    private final JFrame frame;
    private final Airfield<Plane, IFloatForm> airfield;
    private final JTextField textFieldTakePalne;

    public FarmeAirfield() {
        frame = new JFrame("Аэродром");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        airfield = new Airfield<>(700, 450);

        AirfieldPanel airfieldPanel = new AirfieldPanel(airfield);
        JButton buttonCreatePlane = new JButton("Создать самолет");
        JButton buttonCreateSeaPlane = new JButton("Создать гидролет");
        JButton buttonTakePlane = new JButton("Забрать");
        JLabel labelPlace = new JLabel("Место:");
        JLabel labelTakePlane = new JLabel("Забрать самолет:");
        textFieldTakePalne = new JTextField();

        frame.getContentPane().add(airfieldPanel);
        frame.getContentPane().add(buttonCreatePlane);
        frame.getContentPane().add(buttonCreateSeaPlane);
        frame.getContentPane().add(buttonTakePlane);
        frame.getContentPane().add(labelPlace);
        frame.getContentPane().add(labelTakePlane);
        frame.getContentPane().add(textFieldTakePalne);

        airfieldPanel.setBounds(0, 0, 650, 450);
        buttonCreatePlane.setBounds(710, 10, 170, 30);
        buttonCreateSeaPlane.setBounds(710, 50, 170, 30);
        labelPlace.setBounds(710, 100, 170, 20);
        labelTakePlane.setBounds(710, 130, 170, 20);
        textFieldTakePalne.setBounds(710, 160, 170, 20);
        buttonTakePlane.setBounds(710, 190, 170, 30);

        buttonCreatePlane.addActionListener(e -> createPlane());
        buttonCreateSeaPlane.addActionListener(e -> createSeaPlane());
        buttonTakePlane.addActionListener(e -> takePlane());

        frame.repaint();
    }

    private void createPlane() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        if (colorDialog.getColor() != null) {
            Plane plane = new Plane((int) (Math.random() * 100 + 50), (int) (Math.random() * 1000 + 1500), colorDialog.getColor());
            if (airfield.plus(plane)) {
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Аэродром переполнен");
            }
        }
    }

    private void createSeaPlane() {
        JColorChooser mainСolorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, mainСolorDialog);
        if (mainСolorDialog.getColor() != null) {
            JColorChooser dopColorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, dopColorDialog);
            if (dopColorDialog.getColor() != null) {
                SeaPlane plane = new SeaPlane((int) (Math.random() * 100 + 50), (int) (Math.random() * 1000 + 1500), mainСolorDialog.getColor(), dopColorDialog.getColor(), true, true, 0, "Комбинированый");
                if (airfield.plus(plane)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Аэродром переполнен");
                }
            }
        }
    }

    private void takePlane() {
        if (!textFieldTakePalne.getText().equals("")) {
            Plane plane = airfield.minus(Integer.parseInt(textFieldTakePalne.getText()));
            if (plane == null) {
                JOptionPane.showMessageDialog(frame, "Это место пусто");
            } else {
                FrameSeaPlane frameSeaPlane = new FrameSeaPlane();
                frameSeaPlane.setPlane(plane);
                frame.repaint();
            }
        }
    }
}