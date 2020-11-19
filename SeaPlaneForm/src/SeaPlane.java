import java.awt.*;

public class SeaPlane {

    private int _startPosX;
    private int _startPosY;

    private int _frameWidth;
    private int _frameHeight;

    private final int planeWidth = 100;
    private final int planeHeight = 60;

    private int MaxSpeed;
    private float Weight;
    private Color MainColor;
    private Color DopColor;
    private boolean PlaneFloat;
    private boolean LowerWing;
    private PlaneFloat floats;
    public SeaPlane(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean planeFloat,
                    boolean lowerWing,int digit) {
        this.MaxSpeed = maxSpeed;
        this.Weight = weight;
        this.MainColor = mainColor;
        this.DopColor = dopColor;
        this.PlaneFloat = planeFloat;
        this.LowerWing = lowerWing;
        this.floats = new PlaneFloat(digit);
    }

    public int getMaxSpeed() {
        return MaxSpeed;
    }

    private void MaxSpeed(int maxSpeed) {
        this.MaxSpeed = maxSpeed;
    }

    public float getWeight() {
        return Weight;
    }

    private void setWeight(float weight) {
        this.Weight = weight;
    }

    public Color getMainColor() {
        return MainColor;
    }

    private void setMainColor(Color mainColor) {
        this.MainColor = mainColor;
    }

    public Color getDopColor() {
        return DopColor;
    }

    private void setDopColor(Color dopColor) {
        this.DopColor = dopColor;
    }


    public boolean isPlaneFloat() {
        return PlaneFloat;
    }

    private void setPlaneFloat(boolean backLine) {
        this.PlaneFloat = backLine;
    }

    public boolean isLowerWing() {
        return LowerWing;
    }

    private void setLowerWing(boolean lowerWing) {
        this.LowerWing = lowerWing;
    }

    public void setPosition(int posX, int posY, int frameWidth, int frameHeight) {
        this._frameHeight = frameHeight;
        this._frameWidth = frameWidth;
        if (posX >= 0 && posX + planeWidth < frameWidth &&
                posY >= 0 && posY + planeHeight < frameHeight) {
            this._startPosX = posX;
            this._startPosY = posY;
        }
    }

    public void movePlane(Direction direction) {
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

    public void draw(Graphics g) {

        g.setColor(MainColor);
        //body
        g.fillOval(_startPosX + 80, _startPosY+10, 20, 35);
        g.fillRect(_startPosX + 10, _startPosY + 25, 90, 20);
        //wing part 1
        g.fillOval(_startPosX + 30, _startPosY+5 , 40, 5);
        g.setColor(Color.BLACK);
        //wint
        g.drawLine(_startPosX + 10, _startPosY + 35, _startPosX+5, _startPosY+35);
        g.drawLine(_startPosX+5, _startPosY + 30, _startPosX+5, _startPosY + 40);
        //glass
        g.drawLine(_startPosX + 25, _startPosY + 25, _startPosX + 30, _startPosY + 15);
        //wing part 2
        g.drawLine(_startPosX+50, _startPosY + 25, _startPosX+50, _startPosY + 10);



        if (LowerWing)
        {
            g.setColor(DopColor);
            g.fillOval(_startPosX + 30, _startPosY + 30, 40, 5);
        }
        if (PlaneFloat)
        {
            floats.draw(g,DopColor,_startPosX,_startPosY);
        }
    }
}
