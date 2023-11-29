
public class Main {

    void addTry(Point point, MyMadSet set) {
        try {
            set.addPoint(point);
            System.out.println("Punkt " + point + " został prawidłowo dodany");
        } catch (TooCloseException e) {
            System.out.println("Wyjątek! dodawania Lista punktów usuniętych: " + e.removePoints());
        }
    }

    void minAllowedTry(MyMadSet set, double distance) {
        try {
            set.setMinDistanceAllowed(distance);
            System.out.println("Distance Allowed zmieniony lista bez zmian");
        } catch (TooCloseException e) {
            System.out.println("Wyjątek! zmiany odległosci Lista punktów usuniętych: " + e.removePoints());
        }
    }

    public static void main(String[] args) throws TooCloseException {
        Point point1 = new Point(4, 3);
        Point point2 = new Point(6, 5);
        Point point3 = new Point(2, 4);
        Point point4 = new Point(1, 2);
        Point point5 = new Point(1, 2);
        Point point6 = new Point(1, 2);
        Point point7 = new Point(1, 2);
        Point point8 = new Point(1, 2);
        Point point9 = new Point(1, 2);
        Point point10 = new Point(1, 2);


        MyDistanceMeasure measure = new MyDistanceMeasure();
        System.out.println("Kwadrat odległości P(4,3),P(6,5)" + measure.distance(point1, point2));
        System.out.println("Kwadrat odległości P(4,3),P(2,4) " + measure.distance(point1, point3));
        System.out.println("Kwadrat odległości P(6,5),P(2,4) " + measure.distance(point2, point3));
        MyMadSet set1 = new MyMadSet();

        Main main = new Main();
        set1.setDistanceMeasure(measure);
        set1.setMinDistanceAllowed(8); //8


        main.addTry(point1, set1);
        System.out.println("Lista punktów po operacji: " + set1.getPoints());
        main.addTry(point2, set1);
        System.out.println("Lista punktów po operacji: " + set1.getPoints());
        main.addTry(point3, set1);
        System.out.println("Lista punktów po operacji: " + set1.getPoints());
        main.minAllowedTry(set1, 2); //30
        System.out.println("Lista punktów po operacji: " + set1.getPoints());
        System.out.println("Posortowane wzgledem P(4,3)" + set1.getSortedPoints(point1));
    }
}