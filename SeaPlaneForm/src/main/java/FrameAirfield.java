import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class FrameAirfield {
    private static Logger logger;
    private final JFrame frame;
    private final AirfieldPanel airfieldPanel;
    private final Queue<Plane> planeQueue;
    private final AirfieldCollection airfieldCollection;
    private final DefaultListModel<String> airfieldList;
    private final JList<String> listBoxAirfields;
    private final JTextField fieldAirfieldName;
    private final JTextField textFieldTakePalne;

    public FrameAirfield() {
        BasicConfigurator.configure();
        logger = Logger.getLogger("Default");
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

        saveChosenAirfieldItem.addActionListener(e -> saveChosenAirfield());
        loadChosenAirfieldItem.addActionListener(e -> loadChosenAirfield());
        saveAllDataItem.addActionListener(e -> saveAll());
        loadAllDataItem.addActionListener(e -> loadAll());
        buttonCreatePlane.addActionListener(e -> createPlane());
        buttonMoveToQueue.addActionListener(e -> takePlane());
        buttonGetFromQueue.addActionListener(e -> moveToFrame());
        buttonAddAirfield.addActionListener(e -> addAirfield());
        buttonDelAirfield.addActionListener(e -> delAirfield());
        listBoxAirfields.addListSelectionListener(e -> listListener());

        frame.repaint();
    }

    private void createPlane() {
        new FramePlaneConfig(this);
    }

    public void addPlane(Plane plane) {
        if (plane == null || listBoxAirfields.getSelectedIndex() < 0) {
            logger.warn("Самолета нет или его некуда поставить");
            JOptionPane.showMessageDialog(frame, "Самолета нет или его некуда поставить");
        } else {
            try {
                if (airfieldCollection.get(listBoxAirfields.getSelectedValue()).plus(plane)) {
                    logger.info("Добавлен самолет " + plane.toString());
                    frame.repaint();
                } else {
                    logger.info("Самолет не удалось поставить");
                    JOptionPane.showMessageDialog(frame, "Самолет не удалось поставить");
                }
            } catch (AirfieldOverflowException ex) {
                logger.error("Переполнение");
                JOptionPane.showMessageDialog(frame, "Переполнение");
            } catch (Exception ex) {
                logger.fatal("Неизвестная ошибка");
                JOptionPane.showMessageDialog(frame, "Неизвестная ошибка");
            }
        }
    }

    private void takePlane() {
        if (listBoxAirfields.getSelectedIndex() >= 0) {
            if (!textFieldTakePalne.getText().equals("")) {
                try {
                    Plane plane = airfieldCollection.get(listBoxAirfields.getSelectedValue()).minus(Integer.parseInt(textFieldTakePalne.getText()));
                    logger.info("Добавлен самолет " + plane.toString());
                    planeQueue.add(plane);
                    frame.repaint();
                } catch (PlaneNotFoundException ex) {
                    logger.error("Самолёт не найден");
                    JOptionPane.showMessageDialog(frame, "Самолёт не найден");
                } catch (Exception ex) {
                    logger.fatal("Неизвестная ошибка");
                    JOptionPane.showMessageDialog(frame, "Неизвестная ошибка");
                }
            } else {
                logger.warn("Индекс не введен");
                JOptionPane.showMessageDialog(frame, "Индекс не введен");
            }
        } else {
            logger.warn("Аэродром не выбран");
            JOptionPane.showMessageDialog(frame, "Аэродром не выбран");
        }
    }

    private void moveToFrame() {
        if (!planeQueue.isEmpty()) {
            logger.info("Из очереди взят самолет " + planeQueue.peek().toString());
            FrameSeaPlane frameTruck = new FrameSeaPlane();
            frameTruck.setPlane(Objects.requireNonNull(planeQueue.poll()));
            frame.repaint();
        } else {
            logger.warn("Очередь пуста");
            JOptionPane.showMessageDialog(frame, "Очередь пуста");
        }
    }

    private void addAirfield() {
        if (!fieldAirfieldName.getText().equals("")) {
            logger.info("Добавили аэродром " + fieldAirfieldName.getText());
            airfieldCollection.addAirfield(fieldAirfieldName.getText());
            reloadLevels();
            frame.repaint();
        } else {
            logger.warn("Введите название аэродрома");
            JOptionPane.showMessageDialog(frame, "Введите название аэродрома");
        }
    }

    private void delAirfield() {
        if (listBoxAirfields.getSelectedIndex() >= 0) {
            int result = JOptionPane.showConfirmDialog(frame, "Удалить аэродром " + listBoxAirfields.getSelectedValue() + "?");
            if (result == JOptionPane.YES_OPTION) {
                logger.info("Удалили аэродром");
                airfieldCollection.delAirfield(listBoxAirfields.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            logger.warn("Аэродрома не выбран");
            JOptionPane.showMessageDialog(frame, "Аэродрома не выбран");
        }
    }

    private void saveAll() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showDialog(frame, "Save Airfield");
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String filename = fileChooser.getSelectedFile().toString();
                if (filename.contains(".txt")) {
                    airfieldCollection.saveAllData(filename);
                } else {
                    airfieldCollection.saveAllData(filename + ".txt");
                }
                logger.info("Сохранено в файл " + filename);
            } catch (Exception e) {
                logger.fatal("Неизвестная ошибка при сохранении");
                JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при сохранении");
            }
        } else {
            logger.warn("Не удалось сохранить файл");
            JOptionPane.showMessageDialog(frame, "Не удалось сохранить файл");
        }
    }

    private void saveChosenAirfield() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showDialog(frame, "Save Airfield");
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String filename = fileChooser.getSelectedFile().toString();
                if (filename.contains(".txt")) {
                    airfieldCollection.saveChosenAirfieldData(filename, listBoxAirfields.getSelectedValue());
                } else {
                    airfieldCollection.saveChosenAirfieldData(filename + ".txt", listBoxAirfields.getSelectedValue());
                }
                logger.info("Сохранено в файл " + filename);
            } catch (Exception e) {
                logger.fatal("Неизвестная ошибка при сохранении");
                JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при сохранении");
            }
        } else {
            logger.warn("Не удалось сохранить файл");
            JOptionPane.showMessageDialog(frame, "Не удалось сохранить файл");
        }
    }

    private void loadAll() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String filename = fileChooser.getSelectedFile().toString();
                airfieldCollection.loadAllData(filename);
                reloadLevels();
                frame.repaint();
                logger.info("Загружено из файла " + filename);
            } catch (Exception e) {
                logger.fatal("Неизвестная ошибка при загрузке");
                JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при загрузке");
            }
        } else {
            logger.warn("Не удалось загрузить файл");
            JOptionPane.showMessageDialog(frame, "Не удалось загрузить файл");
        }
    }

    private void loadChosenAirfield() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String filename = fileChooser.getSelectedFile().toString();
                airfieldCollection.loadChosenAirfieldData(filename);
                reloadLevels();
                frame.repaint();
                logger.info("Загружено из файла " + filename);
            } catch (Exception e) {
                logger.fatal("Неизвестная ошибка при загрузке");
                JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при загрузке");
            }
        } else {
            logger.warn("Не удалось загрузить файл");
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