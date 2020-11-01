import javax.swing.*;
import java.awt.*;

public class FrameSeaPlane {
    private final JFrame frame;
    private MyPanel myPanel;
    private JComboBox floats;
    private JComboBox floatForm;

    public FrameSeaPlane() {
        frame = new JFrame("Гидролет");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        Icon left = new ImageIcon("resources\\arrowLeft.jpg");
        Icon right = new ImageIcon("resources\\arrowRight.jpg");
        Icon up = new ImageIcon("resources\\arrowUp.jpg");
        Icon down = new ImageIcon("resources\\arrowDown.jpg");

        JButton btnCreatePlane = new JButton("Создать самолет");
        JButton btnCreateSeaPlane = new JButton("Создать гидролет");

        JButton btnUp = new JButton(up);
        btnUp.setName("up");
        JButton btnDown = new JButton(down);
        btnDown.setName("down");
        JButton btnLeft = new JButton(left);
        btnLeft.setName("left");
        JButton btnRight = new JButton(right);
        btnRight.setName("right");

        frame.getContentPane().add(btnCreateSeaPlane);
        frame.getContentPane().add(btnCreatePlane);
        frame.getContentPane().add(btnUp);
        frame.getContentPane().add(btnDown);
        frame.getContentPane().add(btnLeft);
        frame.getContentPane().add(btnRight);
        btnCreateSeaPlane.setBounds(200, 10, 180, 30);
        btnCreatePlane.setBounds(10, 10, 180, 30);
        btnUp.setBounds(805, 375, 30, 30);
        btnDown.setBounds(805, 410, 30, 30);
        btnLeft.setBounds(770, 410, 30, 30);
        btnRight.setBounds(840, 410, 30, 30);

        btnCreateSeaPlane.addActionListener(e -> setSeaPlane());
        btnCreatePlane.addActionListener(e -> setPlane());
        btnUp.addActionListener(e -> setDirection(btnUp));
        btnDown.addActionListener(e -> setDirection(btnDown));
        btnLeft.addActionListener(e -> setDirection(btnLeft));
        btnRight.addActionListener(e -> setDirection(btnRight));

        floatForm = new JComboBox(new String[]{"Прямоугольный", "Овальный", "Комбинированый"});
        frame.getContentPane().add(floatForm);
        floatForm.setBounds(200, 45, 180, 30);

        floats = new JComboBox(new String[]{"2 поплавка", "4 поплавка", "6 поплавков"});
        frame.getContentPane().add(floats);
        floats.setBounds(10, 45, 180, 30);

    }

    public void addMyPanel(MyPanel panel) {
        myPanel = panel;
        frame.getContentPane().add(myPanel);
        myPanel.setBounds(0, 0, 900, 500);
        frame.repaint();
    }

    private void setDirection(JButton button) {
        String name = button.getName();
        switch (name) {
            case "up":
                myPanel.getPlane().MovePlane(Direction.Up);
                break;
            case "down":
                myPanel.getPlane().MovePlane(Direction.Down);
                break;
            case "left":
                myPanel.getPlane().MovePlane(Direction.Left);
                break;
            case "right":
                myPanel.getPlane().MovePlane(Direction.Right);
                break;
        }
        frame.repaint();
    }

    private void setSeaPlane() {
        myPanel.setPlane(new SeaPlane((int) (Math.random() * 100 + 50), (int) (Math.random() * 1000 + 500), Color.RED, Color.GREEN, true, true, floats.getSelectedIndex(), floatForm.getSelectedItem().toString()));
        myPanel.getPlane().SetPosition((int) (Math.random() * 100 + 10), (int) (Math.random() * 100 + 10), 850, 450);
        frame.repaint();
    }

    private void setPlane() {
        myPanel.setPlane(new Plane((int) (Math.random() * 100 + 50), (int) (Math.random() * 1000 + 500), Color.RED));
        myPanel.getPlane().SetPosition((int) (Math.random() * 100 + 10), (int) (Math.random() * 100 + 10), 850, 450);
        frame.repaint();
    }
}