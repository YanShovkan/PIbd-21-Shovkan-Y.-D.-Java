import java.awt.*;

public class Plane extends AirPlane {
    protected int planeWidth = 100;

    protected int planeHeight = 70;

    public Plane(int maxSpeed, float weight, Color mainColor) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    protected Plane(int maxSpeed, float weight, Color mainColor, int planeWidth, int planeHeight) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        this.planeWidth = planeWidth;
        this.planeHeight = planeHeight;
    }

    @Override
    public void MovePlane(Direction direction) {
        int boarderNumber = 10;
        int step = (int) (MaxSpeed * 1000 / Weight);
        switch (direction) {
            case Up:
                if (_startPosY - step > boarderNumber) {
                    _startPosY -= step;
                }
                break;
            case Right:
                if (_startPosX + step < _frameWidth - planeWidth - boarderNumber) {
                    _startPosX += step;
                }
                break;
            case Down:
                if (_startPosY + step < _frameHeight - planeHeight - boarderNumber) {
                    _startPosY += step;
                }
                break;
            case Left:
                if (_startPosX - step > boarderNumber) {
                    _startPosX -= step;
                }
                break;
        }
    }

    @Override
    public void DrawPlane(Graphics g) {

        g.setColor(MainColor);
        //body
        g.fillOval(_startPosX + 80, _startPosY + 10, 20, 35);
        g.fillRect(_startPosX + 10, _startPosY + 25, 90, 20);
        //wing part 1
        g.fillOval(_startPosX + 30, _startPosY + 5, 40, 5);
        g.setColor(Color.BLACK);
        //wint
        g.drawLine(_startPosX + 10, _startPosY + 35, _startPosX + 5, _startPosY + 35);
        g.drawLine(_startPosX + 5, _startPosY + 30, _startPosX + 5, _startPosY + 40);
        //glass
        g.drawLine(_startPosX + 25, _startPosY + 25, _startPosX + 30, _startPosY + 15);
        //wing part 2
        g.drawLine(_startPosX + 50, _startPosY + 25, _startPosX + 50, _startPosY + 10);
    }

    @Override
    public void setNewMainColor(Color MainColor){
        this.MainColor = MainColor;
    }
}