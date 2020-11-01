import java.awt.*;

public class SeaPlane extends Plane {

    private IFloatForm floatForm;
    private Color DopColor;
    private boolean PlaneFloat;
    private boolean LowerWing;

    public SeaPlane(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean planeFloat,
                    boolean lowerWing, int floats, String floatForm) {
        super(maxSpeed, weight, mainColor, 100, 70);
        DopColor = dopColor;
        PlaneFloat = planeFloat;
        LowerWing = lowerWing;
        switch (floatForm) {
            case "Прямоугольный":
                this.floatForm = new squareFloat(floats, dopColor);
                break;
            case "Овальный":
                this.floatForm = new ovalFloat(floats, dopColor);
                break;
            case "Комбинированый":
                this.floatForm = new combinedFloat(floats, dopColor);
                break;
        }
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

    @Override
    public void DrawPlane(Graphics g) {

        super.DrawPlane(g);

        if (LowerWing) {
            g.setColor(DopColor);
            g.fillOval(_startPosX + 30, _startPosY + 30, 40, 5);
        }
        if (PlaneFloat) {
            floatForm.draw(g, _startPosX, _startPosY);
        }
    }
}