import java.awt.*;

public interface IAirTransport {
    void setPosition(int x, int y, int width, int height);

    void movePlane(Direction direction);

    void drawPlane(Graphics g);
}