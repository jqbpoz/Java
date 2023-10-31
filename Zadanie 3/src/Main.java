import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void setPositionMap(int key, Map<Integer, Set<Position>> map, Position position) {
        if (!map.containsKey(key)) {
            map.put(key, new HashSet<>());
        }
        map.get(key).add(position);
    }

    public static void main(String[] args) {
        //first example from the task
        Map<Integer, Set<Position>> map1 = new HashMap<>();
        NumberStatistics date1 = new NumberStatistics();
        setPositionMap(4, map1, new Position(1, 1));
        setPositionMap(2, map1, new Position(9, 1));
        setPositionMap(2, map1, new Position(1, 4));
        setPositionMap(1, map1, new Position(5, 4));
        setPositionMap(1, map1, new Position(1, 5));
        setPositionMap(2, map1, new Position(9, 5));
        setPositionMap(4, map1, new Position(7, 6));
        setPositionMap(1, map1, new Position(3, 8));
        setPositionMap(3, map1, new Position(6, 7));
        setPositionMap(1, map1, new Position(1, 9));
        setPositionMap(3, map1, new Position(9, 9));
        date1.sideLength(9);
        date1.addNumbers(map1);
        System.out.println("Resoult: " + date1.neighbours(new Position(7, 6), 18));
        System.out.println("Expected:{1={18=1, 8=1, 10=1}, 2={5=1, 13=1}, 3={2=1, 13=1}}");
        //secornd example from the task
        Map<Integer, Set<Position>> map2 = new HashMap<>();
        NumberStatistics date2 = new NumberStatistics();
        setPositionMap(2, map2, new Position(4, 4));
        setPositionMap(1, map2, new Position(5, 4));
        setPositionMap(4, map2, new Position(6, 5));
        setPositionMap(1, map2, new Position(5, 6));
        setPositionMap(4, map2, new Position(7, 6));
        setPositionMap(1, map2, new Position(3, 7));
        setPositionMap(3, map2, new Position(6, 7));
        date2.sideLength(9);
        date2.addNumbers(map2);
        System.out.println("Resoult: " + date2.neighbours(new Position(5, 5), 8));
        System.out.println("Expected:{1={1=2, 8=1}, 2={2=1}, 3={5=1}, 4={1=1, 5=1}}");
    }
}
