import java.util.ArrayList;
import java.util.List;

class MyMadSet implements MadSet {

    DistanceMeasure measure;
    double minAllowed;
    List<Point> pointList = new ArrayList<>();
    List<Point> removedPointList = new ArrayList<>();

    public boolean change() {
        removedPointList.clear();
        List<Point> newPointList = new ArrayList<>(pointList);
        for (Point p1 : pointList) {
            for (Point p2 : pointList) {
                if (this.measure.distance(p1, p2) <= minAllowed && !p1.equals(p2)) {
                    newPointList.remove(p1);
                    newPointList.remove(p2);
                    if (!removedPointList.contains(p2)) {
                        removedPointList.add(p2);
                    }
                    if (!removedPointList.contains(p1)) {
                        removedPointList.add(p1);
                    }
                }
            }
        }
        if (!pointList.equals(newPointList)) {
            pointList = newPointList;
            return true;
        }
        return false;

    }


    @Override
    public void setDistanceMeasure(DistanceMeasure measure) throws TooCloseException {
        removedPointList.clear();
        this.measure = measure;
        if (change()) {
            throw new TooCloseException(removedPointList);
        }
    }

    @Override
    public void setMinDistanceAllowed(double minAllowed) throws TooCloseException {
        removedPointList.clear();
        this.minAllowed = minAllowed;
        if (change()) {
            throw new TooCloseException(removedPointList);
        }
    }

    @Override
    public void addPoint(Point point) throws TooCloseException {
        removedPointList.clear();
        List<Point> toAddPointList = new ArrayList<>();
        boolean isValidPoint = true;
        if (!pointList.isEmpty()) {
            for (Point pointInList : pointList) {
                if (measure.distance(pointInList, point) <= minAllowed) {
                    removedPointList.add(pointInList);
                    removedPointList.add(point);
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

        removedPointList.forEach(toRemovePoint -> pointList.remove(toRemovePoint));

        if (!isValidPoint) {
            throw new TooCloseException(removedPointList);
        }
    }

    @Override
    public List<Point> getPoints() {
        return this.pointList;
    }

    @Override
    public List<Point> getSortedPoints(Point referencePoint) {
        List<Point> toSortList = new ArrayList<>(pointList);
        toSortList.sort(((p1, p2) -> sortingFunction(p1, p2, referencePoint)));
        return toSortList;
    }

    public int sortingFunction(Point p1, Point p2, Point referencePoint) {
        double d1 = measure.distance(p1, referencePoint);
        double d2 = measure.distance(p2, referencePoint);
        return Double.compare(d1, d2);
    }
}
