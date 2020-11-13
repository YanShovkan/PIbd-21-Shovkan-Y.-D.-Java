import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class FrameAirfield {

    private final JFrame frame;
    private final AirfieldPanel airfieldPanel;
    private final Queue<Plane> planeQueue;
    private final AirfieldCollection airfieldCollection;
    private final DefaultListModel<String> airfieldList;
    private final JList<String> listBoxAirfields;
    private final JTextField fieldAirfieldName;
    private final JTextField textFieldTakePalne;

    public FrameAirfield() {

        planeQueue = new LinkedList<>();

        frame = new JFrame("Аэродром");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        airfieldCollection = new AirfieldCollection(700, 450);
        airfieldPanel = new AirfieldPanel(airfieldCollection);

        JButton buttonCreatePlane = new JButton("Cамолет");
        JButton buttonCreateSeaPlane = new JButton("Гидролет");
        JLabel labelPlace = new JLabel("Место:");
        JLabel labelTakePlane = new JLabel("Забрать самолет:");
        textFieldTakePalne = new JTextField();
        JButton buttonMoveToQueue = new JButton("Поместить");
        JButton buttonGetFromQueue = new JButton("Получить");

        JLabel labelAirdieldName = new JLabel("Имя аэродрома");
        airfieldList = new DefaultListModel<>();
        listBoxAirfields = new JList<>(airfieldList);
        fieldAirfieldName = new JTextField();
        JButton buttonAddAirfield = new JButton("Добавить аэродром");
        JButton buttonDelAirfield = new JButton("Удалить аэродром");


        frame.getContentPane().add(airfieldPanel);
        frame.getContentPane().add(buttonCreatePlane);
        frame.getContentPane().add(buttonCreateSeaPlane);
        frame.getContentPane().add(labelPlace);
        frame.getContentPane().add(labelTakePlane);
        frame.getContentPane().add(textFieldTakePalne);

        frame.getContentPane().add(buttonMoveToQueue);
        frame.getContentPane().add(buttonGetFromQueue);
        frame.getContentPane().add(labelAirdieldName);
        frame.getContentPane().add(fieldAirfieldName);
        frame.getContentPane().add(buttonAddAirfield);
        frame.getContentPane().add(buttonDelAirfield);
        frame.getContentPane().add(listBoxAirfields);

        labelAirdieldName.setBounds(710, 10, 170, 30);
        fieldAirfieldName.setBounds(710, 50, 170, 30);
        buttonAddAirfield.setBounds(710, 90, 170, 30);
        listBoxAirfields.setBounds(710, 130, 170, 60);
        buttonDelAirfield.setBounds(710, 200, 170, 30);

        airfieldPanel.setBounds(0, 0, 650, 450);
        buttonCreatePlane.setBounds(710, 240, 170, 30);
        buttonCreateSeaPlane.setBounds(710, 280, 170, 30);
        labelPlace.setBounds(710, 330, 170, 20);
        labelTakePlane.setBounds(710, 360, 170, 20);
        textFieldTakePalne.setBounds(710, 390, 170, 20);
        buttonGetFromQueue.setBounds(710, 420, 80, 30);
        buttonMoveToQueue.setBounds(800, 420, 80, 30);

        buttonCreatePlane.addActionListener(e -> createPlane());
        buttonCreateSeaPlane.addActionListener(e -> createSeaPlane());

        buttonMoveToQueue.addActionListener(e -> takePlane());
        buttonGetFromQueue.addActionListener(e -> moveToFrame());
        buttonAddAirfield.addActionListener(e -> addAirfield());
        buttonDelAirfield.addActionListener(e -> delAirfield());
        listBoxAirfields.addListSelectionListener(e -> listListener());

        frame.repaint();
    }

    private void createPlane(){
        if (listBoxAirfields.getSelectedIndex() >= 0) {
            JColorChooser colorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorDialog);
            if (colorDialog.getColor() != null) {
                Plane plane = new Plane((int) (Math.random() * 100 + 50), (int) (Math.random() * 1000 + 1500), colorDialog.getColor());
                if (airfieldCollection.get(listBoxAirfields.getSelectedValue()).plus(plane)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Аэродром переполнен");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Аэродром не выбран");
        }
    }

    private void createSeaPlane() {
        if (listBoxAirfields.getSelectedIndex() >= 0) {
            JColorChooser colorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorDialog);
            if (colorDialog.getColor() != null) {
                JColorChooser otherColorDialog = new JColorChooser();
                JOptionPane.showMessageDialog(frame, otherColorDialog);
                if (otherColorDialog.getColor() != null) {
                    Plane plane = new SeaPlane((int) (Math.random() * 100 + 50), (int) (Math.random() * 1000 + 1500), colorDialog.getColor(), otherColorDialog.getColor(), true, true, 0, "Комбинированый");
                    if (airfieldCollection.get(listBoxAirfields.getSelectedValue()).plus(plane)) {
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Аэродром переполнен");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Аэродром не выбран");
        }
    }

    private void takePlane() {
        if (listBoxAirfields.getSelectedIndex() >= 0) {
            if (!textFieldTakePalne.getText().equals("")) {
                try {
                    Plane plane = airfieldCollection.get(listBoxAirfields.getSelectedValue()).minus(Integer.parseInt(textFieldTakePalne.getText()));
                    if (plane != null) {
                        planeQueue.add(plane);
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Самолёта с таким индексом нет!");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Самолёта с таким индексом нет!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Аэродром не выбран");
        }
    }

    private void moveToFrame() {
        if (!planeQueue.isEmpty()) {
            FrameSeaPlane frameTruck = new FrameSeaPlane();
            frameTruck.setPlane(Objects.requireNonNull(planeQueue.poll()));
            frame.repaint();
        }
    }

    private void addAirfield() {
        if (!fieldAirfieldName.getText().equals("")) {
            airfieldCollection.AddAirfield(fieldAirfieldName.getText());
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Введите название аэродрома");
        }
    }

    private void delAirfield() {
        if (listBoxAirfields.getSelectedIndex() >= 0) {
            int result = JOptionPane.showConfirmDialog(frame, "Удалить аэродром " + listBoxAirfields.getSelectedValue() + "?");
            if (result == JOptionPane.YES_OPTION) {
                airfieldCollection.DelAirfield(listBoxAirfields.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Аэродрома не выбран");
        }
    }

    private void reloadLevels() {
        int index = listBoxAirfields.getSelectedIndex();

        airfieldList.removeAllElements();
        int i = 0;
        for (String name : airfieldCollection.keySet()) {
            airfieldList.add(i, name);
            i++;
        }

        int itemsCount = airfieldList.size();
        if (itemsCount > 0 && (index < 0 || index >= itemsCount)) {
            listBoxAirfields.setSelectedIndex(0);
        } else if (index >= 0 && index < itemsCount) {
            listBoxAirfields.setSelectedIndex(index);
        }
    }

    private void listListener() {
        airfieldPanel.setSelectedItem(listBoxAirfields.getSelectedValue());
        frame.repaint();

    }
}