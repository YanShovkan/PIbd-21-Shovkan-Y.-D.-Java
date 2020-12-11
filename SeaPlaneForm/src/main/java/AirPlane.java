import java.awt.*;

public abstract class AirPlane implements IAirTransport {

    protected int _startPosX;

    protected int _startPosY;

    protected int _frameWidth;

    protected int _frameHeight;

    public int maxSpeed;

    protected void setMaxSpeed(int MaxSpeed) {
        this.maxSpeed = MaxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int weight;

    protected void setWeight(int Weight) {
        this.weight = Weight;
    }

    public int getWeight() {
        return weight;
    }

    public Color mainColor;

    protected void setMainColor(Color MainColor) {
        this.mainColor = MainColor;
    }

    public Color getMainColor() {
        return mainColor;
    }

    public void setPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _frameWidth = width;
        _frameHeight = height;
    }

    public abstract void drawPlane(Graphics g);

    public abstract void movePlane(Direction direction);

    public abstract void setNewMainColor(Color MainColor);

}