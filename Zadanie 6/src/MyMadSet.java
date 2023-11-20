import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyMadSet implements MadSet {

    private DistanceMeasure measure;
    private double minAllowed;
    List<Point> pointList = new ArrayList<>();
    List<Point> removedPoints = new ArrayList<>();

    public boolean changeDetector() {
        List<Point> oldPointList = new ArrayList<>(pointList);
        for (Point point1 : pointList) {
            for (Point point2 : pointList) {
                if (measure.distance(point1, point2) >= minAllowed) {
                    oldPointList.remove(point1);
                    oldPointList.remove(point2);
                    if (!removedPoints.contains(point1)) {
                        removedPoints.add(point1);
                    }
                    if (!removedPoints.contains(point2)) {
                        removedPoints.add(point2);
                    }
                }
            }
        }
        if (!removedPoints.equals(oldPointList)) {
            pointList = oldPointList;
            return true; // dane zostały zmodyfikowane
        }
        return false; // lista nie uległa zmianie

    }


    @Override
    public void setDistanceMeasure(DistanceMeasure measure) throws TooCloseException {
        this.measure = measure;
        if (changeDetector()) {
            throw new TooCloseException(removedPoints);
        }
    }

    @Override
    public void setMinDistanceAllowed(double minAllowed) throws TooCloseException {
        this.minAllowed = minAllowed;
        if (changeDetector()) {
            throw new TooCloseException(removedPoints);
        }
    }

    @Override
    public void addPoint(Point point) throws TooCloseException {
        List<Point> oldPointList = new ArrayList<>(pointList);
        boolean isValidPoint = true;
        if (!pointList.isEmpty()) {
            for (Point pointInList : oldPointList) {
                if (measure.distance(pointInList, point) < minAllowed) {
                    pointList.remove(pointInList);
                    removedPoints.add(pointInList);
                    removedPoints.add(point);
                    isValidPoint = false;
                } else {
                    pointList.add(point);
                }
            }
        } else {
            pointList.add(point);
        }
        if (!isValidPoint) {
            throw new TooCloseException(removedPoints);
        }
    }

    @Override
    public List<Point> getPoints() {
        return pointList;
    }

    @Override
    public List<Point> getSortedPoints(Point referencePoint) {
        List<Point> sortedList = new ArrayList<>(pointList);
        sortedList.sort(Comparator.comparingDouble(point -> measure.distance(point, referencePoint)));
        return sortedList;
    }
}
