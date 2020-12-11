import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Airfield<T extends IAirTransport, K extends IFloatForm> {
    private final List<T> _places;

    private final int _maxCount;

    private final int pictureWidth;

    private final int pictureHeight;

    private final int _placeSizeWidth = 150;

    private final int _placeSizeHeight = 80;

    public Airfield(int picWidth, int picHeight) {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;
        _maxCount = width * height;
        _places = new ArrayList<>();
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    public boolean plus(T plane) throws AirfieldOverflowException {
        if (_places.size() < _maxCount) {
            _places.add(plane);
            return true;
        }
        throw new AirfieldOverflowException();
    }

    public T minus(int index) throws PlaneNotFoundException {
        if (index >= 0 && index < _maxCount ) {
            try {
                T plane = _places.get(index);
                _places.remove(index);
                return plane;
            } catch (Exception ex) {
                throw new PlaneNotFoundException(index);
            }
        }
        throw new PlaneNotFoundException(index);
    }

    public boolean bolsheRavno(Plane plane1, Plane plane2) {
        return (plane1.getWeight() >= plane2.getWeight());
    }

    public boolean mensheRavno(Plane plane1, Plane plane2) {
        return (plane1.getWeight() <= plane2.getWeight());
    }

    public void deleteAllPlane() {
        _places.clear();
    }

    public T getAirPlane(int index) {
        if (index >= 0 && index < _places.size()) {
            return _places.get(index);
        }
        return null;
    }

    public void draw(Graphics g) {
        drawMarking(g);
        for (int i = 0; i < _places.size(); i++) {
            _places.get(i).setPosition(10 + _placeSizeWidth * (i / 5), 10 + _placeSizeHeight * (i % 5), pictureWidth, pictureHeight);
            _places.get(i).drawPlane(g);
        }
    }

    private void drawMarking(Graphics g) {
        for (int i = 0; i < pictureWidth / _placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j) {
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + _placeSizeWidth * 4 / 5, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, (pictureHeight / _placeSizeHeight) * _placeSizeHeight);
        }
    }
}