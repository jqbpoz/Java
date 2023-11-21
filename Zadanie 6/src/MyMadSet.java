import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyMadSet implements MadSet {

    private DistanceMeasure measure;
    private double minAllowed;
    List<Point> pointList = new ArrayList<>();
    List<Point> removedPoints = new ArrayList<>();

    public boolean change() {
        removedPoints.clear();
        List<Point> oldPointList = new ArrayList<>(pointList);
        for (Point point1 : pointList) {
            for (Point point2 : pointList) {
                if (measure.distance(point1, point2) < minAllowed) {
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
            return true;
        }
        return false;

    }


    @Override
    public void setDistanceMeasure(DistanceMeasure measure) throws TooCloseException {
        this.measure = measure;
        if (change()) {
            throw new TooCloseException(removedPoints);
        }
    }

    @Override
    public void setMinDistanceAllowed(double minAllowed) throws TooCloseException {
        this.minAllowed = minAllowed;
        if (change()) {
            throw new TooCloseException(removedPoints);
        }
    }

    @Override
    public void addPoint(Point point) throws TooCloseException {
        removedPoints.clear();
        List<Point> toAddPointList = new ArrayList<>();
        boolean isValidPoint = true;
        if (!pointList.isEmpty()) {
            for (Point pointInList : pointList) {
                if (measure.distance(pointInList, point) < minAllowed) {
                    removedPoints.add(pointInList);
                    removedPoints.add(point);
                    isValidPoint = false;
                    continue;
                }
                toAddPointList.add(point);
            }

        } else {
            toAddPointList.add(point);
        }
        toAddPointList.forEach(toAddPoint -> {
            if (!pointList.contains(toAddPoint)) {
                pointList.add(toAddPoint);
            }
        });
        removedPoints.forEach(toRemovePoint -> pointList.remove(toRemovePoint));

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
