import java.awt.*;

public class PlaneFloat {
    private EnumerationOfFloats floats;

    public PlaneFloat(int number) {
        setDigit(number);
    }

    public void setDigit(int number) {
        this.floats = EnumerationOfFloats.getChosenNumber(number);
    }

    public void draw(Graphics g, Color DopColor, int _startPosX, int _startPosY) {
        if(floats == EnumerationOfFloats.Two){
            g.setColor(Color.BLACK);
            g.drawLine(_startPosX + 30, _startPosY + 45, _startPosX + 30, _startPosY + 55);
            g.drawLine(_startPosX + 60, _startPosY + 45, _startPosX + 60, _startPosY + 55);
            g.setColor(DopColor);
            g.fillRect(_startPosX + 10, _startPosY + 55, 70, 5);
        }
        if(floats == EnumerationOfFloats.Four){
            g.setColor(Color.BLACK);
            g.drawLine(_startPosX + 30, _startPosY + 45, _startPosX + 30, _startPosY + 55);
            g.drawLine(_startPosX + 45, _startPosY + 45, _startPosX + 45, _startPosY + 55);
            g.drawLine(_startPosX + 60, _startPosY + 45, _startPosX + 60, _startPosY + 55);
            g.drawLine(_startPosX + 75, _startPosY + 45, _startPosX + 75, _startPosY + 55);
            g.setColor(DopColor);
            g.fillRect(_startPosX + 20, _startPosY + 55, 30, 5);
            g.fillRect(_startPosX + 55, _startPosY + 55, 30, 5);
        }
        if(floats == EnumerationOfFloats.Six){
            g.setColor(Color.BLACK);
            g.drawLine(_startPosX + 20, _startPosY + 45, _startPosX + 20, _startPosY + 55);
            g.drawLine(_startPosX + 35, _startPosY + 45, _startPosX + 35, _startPosY + 55);
            g.drawLine(_startPosX + 50, _startPosY + 45, _startPosX + 50, _startPosY + 55);
            g.drawLine(_startPosX + 65, _startPosY + 45, _startPosX + 65, _startPosY + 55);
            g.drawLine(_startPosX + 80, _startPosY + 45, _startPosX + 80, _startPosY + 55);
            g.drawLine(_startPosX + 95, _startPosY + 45, _startPosX + 95, _startPosY + 55);
            g.setColor(DopColor);
            g.fillRect(_startPosX + 15, _startPosY + 55, 25, 5);
            g.fillRect(_startPosX + 45, _startPosY + 55, 25, 5);
            g.fillRect(_startPosX + 75, _startPosY + 55, 25, 5);
        }
    }

}
