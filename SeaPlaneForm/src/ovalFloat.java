import java.awt.*;

public class ovalFloat implements IFloatForm {

    private EnumerationOfFloats floats;

    public ovalFloat(int number) {
        setDigit(number);
    }

    public void setDigit(int number) {
        this.floats = EnumerationOfFloats.getChosenNumber(number);
    }

    public void draw(Graphics g, int _startPosX, int _startPosY,Color dopColor) {
        g.setColor(Color.BLACK);
        if (floats == EnumerationOfFloats.Two) {
            g.drawLine(_startPosX + 30, _startPosY + 45, _startPosX + 30, _startPosY + 55);
            g.drawLine(_startPosX + 60, _startPosY + 45, _startPosX + 60, _startPosY + 55);
            g.setColor(dopColor);
            g.fillOval(_startPosX + 10, _startPosY + 55, 70, 5);
        }
        if (floats == EnumerationOfFloats.Four) {
            g.drawLine(_startPosX + 28, _startPosY + 45, _startPosX + 28, _startPosY + 55);
            g.drawLine(_startPosX + 43, _startPosY + 45, _startPosX + 43, _startPosY + 55);
            g.drawLine(_startPosX + 63, _startPosY + 45, _startPosX + 63, _startPosY + 55);
            g.drawLine(_startPosX + 78, _startPosY + 45, _startPosX + 78, _startPosY + 55);
            g.setColor(dopColor);
            g.fillOval(_startPosX + 23, _startPosY + 55, 28, 5);
            g.fillOval(_startPosX + 58, _startPosY + 55, 28, 5);
        }
        if (floats == EnumerationOfFloats.Six) {
            g.drawLine(_startPosX + 20, _startPosY + 45, _startPosX + 20, _startPosY + 55);
            g.drawLine(_startPosX + 35, _startPosY + 45, _startPosX + 35, _startPosY + 55);
            g.drawLine(_startPosX + 50, _startPosY + 45, _startPosX + 50, _startPosY + 55);
            g.drawLine(_startPosX + 65, _startPosY + 45, _startPosX + 65, _startPosY + 55);
            g.drawLine(_startPosX + 80, _startPosY + 45, _startPosX + 80, _startPosY + 55);
            g.drawLine(_startPosX + 95, _startPosY + 45, _startPosX + 95, _startPosY + 55);
            g.setColor(dopColor);
            g.fillOval(_startPosX + 15, _startPosY + 55, 25, 5);
            g.fillOval(_startPosX + 45, _startPosY + 55, 25, 5);
            g.fillOval(_startPosX + 75, _startPosY + 55, 25, 5);
        }
    }
}