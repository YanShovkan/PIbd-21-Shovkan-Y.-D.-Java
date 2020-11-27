import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;
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
        frame.setSize(900, 530);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);

        JMenu saveMenu = new JMenu("Сохранить");
        fileMenu.add(saveMenu);

        JMenu loadMenu = new JMenu("Загрузить");
        fileMenu.add(loadMenu);

        JMenuItem saveAllDataItem = new JMenuItem("Сохранить целиком");
        saveMenu.add(saveAllDataItem);

        JMenuItem loadAllDataItem = new JMenuItem("Загрузить целиком");
        loadMenu.add(loadAllDataItem);

        JMenuItem saveChosenAirfieldItem = new JMenuItem("Сохранить один аэродром");
        saveMenu.add(saveChosenAirfieldItem);

        JMenuItem loadChosenAirfieldItem = new JMenuItem("Загрузить один аэродром");
        loadMenu.add(loadChosenAirfieldItem);

        airfieldCollection = new AirfieldCollection(600, 450);
        airfieldPanel = new AirfieldPanel(airfieldCollection);
        airfieldPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JButton buttonCreatePlane = new JButton("Создать самолет");
        JLabel labelPlace = new JLabel("Место:");
        JLabel labelTakePlane = new JLabel("Забрать самолет:");
        textFieldTakePalne = new JTextField();
        JButton buttonMoveToQueue = new JButton("Поместить ");
        JButton buttonGetFromQueue = new JButton("Получить");

        JLabel labelAirdieldName = new JLabel("Имя аэродрома");
        airfieldList = new DefaultListModel<>();
        listBoxAirfields = new JList<>(airfieldList);
        fieldAirfieldName = new JTextField();
        JButton buttonAddAirfield = new JButton("Добавить аэродром");
        JButton buttonDelAirfield = new JButton("Удалить аэродром");

        frame.getContentPane().add(airfieldPanel);
        frame.getContentPane().add(buttonCreatePlane);
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

        airfieldPanel.setBounds(0, 0, 650, 450);
        labelAirdieldName.setBounds(610, 10, 270, 30);
        fieldAirfieldName.setBounds(610, 50, 270, 30);
        buttonAddAirfield.setBounds(610, 90, 270, 30);
        listBoxAirfields.setBounds(610, 130, 270, 60);
        buttonDelAirfield.setBounds(610, 200, 270, 30);
        buttonCreatePlane.setBounds(610, 240, 270, 70);
        labelPlace.setBounds(610, 330, 270, 20);
        labelTakePlane.setBounds(610, 360, 270, 20);
        textFieldTakePalne.setBounds(610, 390, 270, 20);
        buttonGetFromQueue.setBounds(610, 420, 130, 30);
        buttonMoveToQueue.setBounds(750, 420, 130, 30);

        saveChosenAirfieldItem.addActionListener(e -> {
            try {
                saveChosenAirfield();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        loadChosenAirfieldItem.addActionListener(e -> {
            try {
                loadChosenAirfield();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        saveAllDataItem.addActionListener(e -> {
            try {
                saveAll();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        loadAllDataItem.addActionListener(e -> {
            try {
                loadAll();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        buttonCreatePlane.addActionListener(e -> createPlane());
        buttonMoveToQueue.addActionListener(e -> takePlane());
        buttonGetFromQueue.addActionListener(e -> moveToFrame());
        buttonAddAirfield.addActionListener(e -> addAirfield());
        buttonDelAirfield.addActionListener(e -> delAirfield());
        listBoxAirfields.addListSelectionListener(e -> listListener());

        frame.repaint();
    }

    private void createPlane() {
        FramePlaneConfig framePlaneConfig = new FramePlaneConfig(this);
    }

    public void addPlane(Plane plane) {
        if (plane != null && listBoxAirfields.getSelectedIndex() >= 0) {
            if (((airfieldCollection.get(listBoxAirfields.getSelectedValue()).plus(plane)))) {
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Самолет не удалось поставить");
            }
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
            airfieldCollection.addAirfield(fieldAirfieldName.getText());
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
                airfieldCollection.delAirfield(listBoxAirfields.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Аэродрома не выбран");
        }
    }

    private void saveAll() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showDialog(frame, "Save Airfield");
        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().toString();
            if (filename.contains(".txt")) {
                airfieldCollection.saveAllData(filename);
            } else {
                airfieldCollection.saveAllData(filename + ".txt");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Не удалось сохранить файл");
        }
    }

    private void saveChosenAirfield() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showDialog(frame, "Save Airfield");
        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().toString();
            if (filename.contains(".txt")) {
                airfieldCollection.saveChosenAirfieldData(filename, listBoxAirfields.getSelectedValue());
            } else {
                airfieldCollection.saveChosenAirfieldData(filename + ".txt", listBoxAirfields.getSelectedValue());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Не удалось сохранить файл");
        }
    }

    private void loadAll() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().toString();
            airfieldCollection.loadAllData(filename);
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Не удалось загрузить файл");
        }
    }

    private void loadChosenAirfield() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().toString();
            airfieldCollection.loadChosenAirfieldData(filename);
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Не удалось загрузить файл");
        }
    }

    private void reloadLevels() {
        int index = listBoxAirfields.getSelectedIndex();

        airfieldList.removeAllElements();

        for (int i = 0; i < airfieldCollection.keySet().size(); i++) {
            airfieldList.add(i, airfieldCollection.keySet().toArray()[i].toString());
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