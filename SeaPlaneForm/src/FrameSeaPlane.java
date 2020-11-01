import javax.swing.*;
import java.awt.*;

public class FrameSeaPlane {
    private final JFrame frame;
    private GamePanel gamePanel;
    private JComboBox floats;
    private JComboBox floatForm;

    public void setPlane(IAirTransport plane) {
        plane.SetPosition((int) (10 + Math.random() * 90), (int) (100 + Math.random() * 100), frame.getWidth(), frame.getHeight());
        gamePanel.setPlane(plane);
        frame.repaint();
    }

    public FrameSeaPlane() {
        frame = new JFrame("Гидролет");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

        gamePanel = new GamePanel();
        frame.getContentPane().add(gamePanel);
        gamePanel.setBounds(0, 0, 900, 500);
        frame.repaint();
    }

    private void setDirection(JButton button) {
        String name = button.getName();
        switch (name) {
            case "up":
                gamePanel.getPlane().MovePlane(Direction.Up);
                break;
            case "down":
                gamePanel.getPlane().MovePlane(Direction.Down);
                break;
            case "left":
                gamePanel.getPlane().MovePlane(Direction.Left);
                break;
            case "right":
                gamePanel.getPlane().MovePlane(Direction.Right);
                break;
        }
        frame.repaint();
    }

    private void setSeaPlane() {
        gamePanel.setPlane(new SeaPlane((int) (Math.random() * 100 + 50), (int) (Math.random() * 1000 + 1500), Color.RED, Color.GREEN, true, true, floats.getSelectedIndex(), floatForm.getSelectedItem().toString()));
        gamePanel.getPlane().SetPosition((int) (Math.random() * 100 + 10), (int) (Math.random() * 100 + 10), 850, 450);
        frame.repaint();
    }

    private void setPlane() {
        gamePanel.setPlane(new Plane((int) (Math.random() * 100 + 50), (int) (Math.random() * 1000 + 1500), Color.RED));
        gamePanel.getPlane().SetPosition((int) (Math.random() * 100 + 10), (int) (Math.random() * 100 + 10), 850, 450);
        frame.repaint();
    }
}