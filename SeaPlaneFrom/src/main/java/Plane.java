import java.awt.*;
import java.lang.Object;
import java.util.Iterator;
import java.util.LinkedList;


public class Plane extends AirPlane implements Iterator<Object>,Iterable<Object>,Comparable<Plane> {
    public LinkedList<Object> objectProperties= new LinkedList<Object>();

    protected int planeWidth = 100;

    protected int planeHeight = 70;

    protected final String separator = ";";

    protected final String separatorForColor = "-";

    private int currentIndex =  0;
    @Override
    public boolean hasNext() {
        return currentIndex++ < 3;
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

    public Plane(int maxSpeed, int weight, Color mainColor) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
    }

    public Plane(String info) {
        String[] infoStrs = info.split(separator);
        if (infoStrs.length == 3) {
            maxSpeed = Integer.parseInt(infoStrs[0]);
            objectProperties.add(maxSpeed);
            weight = Integer.parseInt(infoStrs[1]);
            objectProperties.add(weight);
            String[] colorStrs = infoStrs[2].split(separatorForColor);
            mainColor = new Color(Integer.parseInt(colorStrs[0]), Integer.parseInt(colorStrs[1]), Integer.parseInt(colorStrs[2]));
            objectProperties.add(mainColor);
        }
    }

    protected Plane(int maxSpeed, int weight, Color mainColor, int planeWidth, int planeHeight) {
        this.maxSpeed = maxSpeed;
        objectProperties.add(maxSpeed);
        this.weight = weight;
        objectProperties.add(weight);
        this.mainColor = mainColor;
        objectProperties.add(mainColor);
        this.planeWidth = planeWidth;
        this.planeHeight = planeHeight;
    }

    @Override
    public void movePlane(Direction direction) {
        int boarderNumber = 10;
        int step = (int) (maxSpeed * 1000 / weight);
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
    public void drawPlane(Graphics g) {

        g.setColor(mainColor);
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
    public void setNewMainColor(Color MainColor) {
        this.mainColor = MainColor;
    }

    @Override
    public String toString() {
        return maxSpeed + separator + weight + separator + mainColor.getRed() + separatorForColor + mainColor.getGreen() + separatorForColor + mainColor.getBlue();
    }

    public boolean Equals(Plane other) {
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
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Plane)) {
            return false;
        } else {
            return Equals((Plane) obj);
        }
    }

    public int compareTo(Plane anotherPlane) {
        if(this.maxSpeed != anotherPlane.maxSpeed) {
            return this.maxSpeed - anotherPlane.maxSpeed;
        }
        if(this.weight != anotherPlane.weight){
            return this.weight - anotherPlane.weight;
        }
        if(this.mainColor.getRGB() != anotherPlane.mainColor.getRGB()){
            return this.mainColor.getRGB() - anotherPlane.mainColor.getRGB();
        }
        return 0;
    }
}