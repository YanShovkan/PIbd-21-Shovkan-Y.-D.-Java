import java.awt.*;

public interface IFloatForm {

    int getNumber();

    void setDigit(int number);

    void draw(Graphics g, int _startPosX, int _startPosY,Color dopColor);
}