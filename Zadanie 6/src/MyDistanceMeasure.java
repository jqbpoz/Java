public class MyDistanceMeasure implements DistanceMeasure{
    @Override
    public double distance(Point a, Point b) {
        return (a.x()- b.x()) * (a.x()- b.x()) + (a.y()- b.y())*(a.y()- b.y());
    }
}
