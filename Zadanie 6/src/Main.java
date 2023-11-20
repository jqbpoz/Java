public class Main {
    public static void main(String[] args) throws TooCloseException {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(2, 2);
        Point point3 = new Point(3, 3);
        Point point4 = new Point(1, 2);
        Point point5 = new Point(1, 2);
        Point point6 = new Point(1, 2);
        Point point7 = new Point(1, 2);
        Point point8 = new Point(1, 2);
        Point point9 = new Point(1, 2);
        Point point10 = new Point(1, 2);

        MyDistanceMeasure measure = new MyDistanceMeasure();

        MyMadSet set1 = new MyMadSet();
        set1.setDistanceMeasure(measure);
        set1.setMinDistanceAllowed(0);
        set1.addPoint(point2);
        set1.addPoint(point3);
        System.out.println(set1.getPoints());


    }
}