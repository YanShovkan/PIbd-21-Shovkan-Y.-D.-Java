import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class SeaPlane extends Plane implements Iterator<Object>,Iterable<Object>,Comparable<Plane> {
    public LinkedList<Object> objectProperties = new LinkedList<Object>();

    private IFloatForm floatForm;
    private Color dopColor;
    private boolean planeFloat;
    private boolean lowerWing;
    private int floats;
    private String floatFormStr;

    private int currentIndex =  0;
    @Override
    public boolean hasNext() {
        return currentIndex++ < 8;
    }

    @Override
    public Object next() {
        return objectProperties.get(currentIndex);
    }

    @Override
    public void remove() {
        objectProperties.remove(currentIndex);
    }

    @Override
    public Iterator<Object> iterator() {
        return objectProperties.iterator();
    }

    public SeaPlane(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean planeFloat,
                    boolean lowerWing, int floats, String floatFormStr) {
        super(maxSpeed, weight, mainColor, 100, 70);
        objectProperties.add(maxSpeed);
        objectProperties.add(weight);
        objectProperties.add(mainColor);
        this.dopColor = dopColor;
        objectProperties.add(dopColor);
        this.planeFloat = planeFloat;
        objectProperties.add(planeFloat);
        this.lowerWing = lowerWing;
        objectProperties.add(lowerWing);
        this.floats = floats;
        objectProperties.add(floats);
        switch (floatFormStr) {
            case "Прямоугольный":
                this.floatForm = new squareFloat(floats);
                this.floatFormStr = floatFormStr;
                break;
            case "Овальный":
                this.floatForm = new ovalFloat(floats);
                this.floatFormStr = floatFormStr;
                break;
            case "Комбинированый":
                this.floatForm = new combinedFloat(floats);
                this.floatFormStr = floatFormStr;
                break;
        }
        objectProperties.add(floatFormStr);
    }

    public SeaPlane(String info) {
        super(info);
        String[] infoStrs = info.split(separator);
        if (infoStrs.length == 8) {
            maxSpeed = Integer.parseInt(infoStrs[0]);
            objectProperties.add(maxSpeed);
            weight = Integer.parseInt(infoStrs[1]);
            objectProperties.add(weight);
            String[] mainColorStrs = infoStrs[2].split(separatorForColor);
            mainColor = new Color(Integer.parseInt(mainColorStrs[0]), Integer.parseInt(mainColorStrs[1]), Integer.parseInt(mainColorStrs[2]));
            objectProperties.add(mainColor);
            String[] dopColorStrs = infoStrs[3].split(separatorForColor);
            dopColor = new Color(Integer.parseInt(dopColorStrs[0]), Integer.parseInt(dopColorStrs[1]), Integer.parseInt(dopColorStrs[2]));
            objectProperties.add(dopColor);
            planeFloat = Boolean.parseBoolean(infoStrs[4]);
            objectProperties.add(planeFloat);
            lowerWing = Boolean.parseBoolean(infoStrs[5]);
            objectProperties.add(lowerWing);
            floats = Integer.parseInt(infoStrs[6]);
            objectProperties.add(floats);
            switch (infoStrs[7]) {
                case "Прямоугольный":
                    this.floatForm = new squareFloat(floats);
                    break;
                case "Овальный":
                    this.floatForm = new ovalFloat(floats);
                    break;
                case "Комбинированый":
                    this.floatForm = new combinedFloat(floats);
                    break;
            }
            objectProperties.add(floatFormStr);
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

    public String getFloatFormStr() {
        return floatFormStr;
    }

    public int getFloats() {
        return floats;
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
        if (floatForm instanceof squareFloat) {
            this.floatFormStr = "Прямоугольный";
        }
        if (floatForm instanceof ovalFloat) {
            this.floatFormStr = "Овальный";
        }
        if (floatForm instanceof combinedFloat) {
            this.floatFormStr = "Комбинированый";
        }
        this.floats = floatForm.getNumber();
    }

    @Override
    public String toString() {
        return super.toString() + separator + dopColor.getRed() + separatorForColor + dopColor.getGreen() +
                separatorForColor + dopColor.getBlue() + separator + planeFloat + separator + lowerWing +
                separator + floats + separator + floatFormStr;
    }

    public boolean Equals(SeaPlane other) {
        if (other == null) {
            return false;
        }
        if (this.getClass().getName() != other.getClass().getName()) {
            return false;
        }
        if (this.maxSpeed != other.maxSpeed) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        if (this.mainColor != other.mainColor) {
            return false;
        }
        if (this.dopColor != other.dopColor) {
            return false;
        }
        if (this.planeFloat != other.planeFloat) {
            return false;
        }
        if (this.lowerWing != other.lowerWing) {
            return false;
        }
        if (this.floatFormStr != other.floatFormStr) {
            return false;
        }
        if (this.floats != other.floats) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SeaPlane)) {
            return false;
        } else {
            return Equals((SeaPlane) obj);
        }
    }

    public int compareTo(SeaPlane anotherPlane) {
        if (this.maxSpeed != anotherPlane.maxSpeed) {
            return this.maxSpeed - anotherPlane.maxSpeed;
        }
        if (this.weight != anotherPlane.weight) {
            return this.weight - anotherPlane.weight;
        }
        if (this.mainColor.getRGB() != anotherPlane.mainColor.getRGB()) {
            return this.mainColor.getRGB() - anotherPlane.mainColor.getRGB();
        }
        if (this.dopColor.getRGB() != anotherPlane.dopColor.getRGB()) {
            return this.dopColor.getRGB() - anotherPlane.dopColor.getRGB();
        }
        if (this.planeFloat != anotherPlane.planeFloat) {
            if (this.planeFloat) {
                return 1;
            }
            return -1;
        }
        if (this.lowerWing != anotherPlane.lowerWing) {
            if (this.lowerWing) {
                return 1;
            }
            return -1;
        }
        if (this.floats != anotherPlane.floats) {
            return this.floats - anotherPlane.floats;
        }
        if (this.floatFormStr != anotherPlane.floatFormStr) {
            int firstPlaneFloatID = 0;
            int secondPlaneFloatID = 0;
            switch (this.floatFormStr) {
                case "Прямоугольный":
                    firstPlaneFloatID = 1;
                    break;
                case "Овальный":
                    firstPlaneFloatID = 2;
                    break;
                case "Комбинированый":
                    firstPlaneFloatID = 3;
                    break;
            }
            switch (anotherPlane.floatFormStr) {
                case "Прямоугольный":
                    secondPlaneFloatID = 1;
                    break;
                case "Овальный":
                    secondPlaneFloatID = 2;
                    break;
                case "Комбинированый":
                    secondPlaneFloatID = 3;
                    break;
            }
            return firstPlaneFloatID - secondPlaneFloatID;
        }
        return 0;
    }
}