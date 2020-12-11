import java.awt.*;

public class SeaPlane extends Plane {

    private IFloatForm floatForm;
    private Color dopColor;
    private boolean planeFloat;
    private boolean lowerWing;
    private int floats;
    private String floatFormStr;

    public SeaPlane(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean planeFloat,
                    boolean lowerWing, int floats, String floatFormStr) {
        super(maxSpeed, weight, mainColor, 100, 70);
        this.dopColor = dopColor;
        this.planeFloat = planeFloat;
        this.lowerWing = lowerWing;
        this.floats = floats;
        switch (floatFormStr) {
            case "Прямоугольный":
                this.floatForm = new squareFloat(floats);
                this.floatFormStr = floatFormStr;

                break;
            case "Овальный":
                this.floatForm = new ovalFloat(floats);
                this.floatFormStr = floatFormStr;
                this.floats = floats;
                break;
            case "Комбинированый":
                this.floatForm = new combinedFloat(floats);
                this.floatFormStr = floatFormStr;
                this.floats = floats;
                break;
        }
    }

    public SeaPlane(String info) {
        super(info);
        String[] infoStrs = info.split(separator);
        if (infoStrs.length == 8) {
            maxSpeed = Integer.parseInt(infoStrs[0]);
            weight = Integer.parseInt(infoStrs[1]);
            String[] mainColorStrs = infoStrs[2].split(separatorForColor);
            mainColor = new Color(Integer.parseInt(mainColorStrs[0]), Integer.parseInt(mainColorStrs[1]), Integer.parseInt(mainColorStrs[2]));
            String[] dopColorStrs = infoStrs[3].split(separatorForColor);
            dopColor = new Color(Integer.parseInt(dopColorStrs[0]), Integer.parseInt(dopColorStrs[1]), Integer.parseInt(dopColorStrs[2]));
            planeFloat = Boolean.parseBoolean(infoStrs[4]);
            lowerWing = Boolean.parseBoolean(infoStrs[5]);
            switch (infoStrs[7]) {
                case "Прямоугольный":
                    this.floatForm = new squareFloat(Integer.parseInt(infoStrs[6]));
                    break;
                case "Овальный":
                    this.floatForm = new ovalFloat(Integer.parseInt(infoStrs[6]));
                    break;
                case "Комбинированый":
                    this.floatForm = new combinedFloat(Integer.parseInt(infoStrs[6]));
                    break;
            }
        }
    }


    public Color getDopColor() {
        return dopColor;
    }

    private void setDopColor(Color dopColor) {
        this.dopColor = dopColor;
    }

    public boolean isPlaneFloat() {
        return planeFloat;
    }

    private void setPlaneFloat(boolean backLine) {
        this.planeFloat = backLine;
    }

    public boolean isLowerWing() {
        return lowerWing;
    }

    private void setLowerWing(boolean lowerWing) {
        this.lowerWing = lowerWing;
    }

    @Override
    public void drawPlane(Graphics g) {

        super.drawPlane(g);

        if (lowerWing) {
            g.setColor(dopColor);
            g.fillOval(_startPosX + 30, _startPosY + 30, 40, 5);
        }
        if (planeFloat) {
            floatForm.draw(g, _startPosX, _startPosY, dopColor);
        }
    }

    public void setNewDopColor(Color dopColor) {
        this.dopColor = dopColor;
    }

    public void setFloatForm(IFloatForm floatForm) {
        this.floatForm = floatForm;
    }

    @Override
    public String toString() {
        return super.toString() + separator + dopColor.getRed() + separatorForColor + dopColor.getGreen() +
                separatorForColor + dopColor.getBlue() + separator + planeFloat + separator + lowerWing +
                separator + floats + separator + floatFormStr;
    }
}