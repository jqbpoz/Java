import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Map<Integer, Set<Position>> map1 = new HashMap<>();
        Map<Integer, Set<Position>> map2 = new HashMap<>();
        NumberStatistics date1 = new NumberStatistics();
        NumberStatistics date2 = new NumberStatistics();
        SetPositionMap.setPositionMap(4, map1, new Position(1, 1));
        SetPositionMap.setPositionMap(2, map1, new Position(9, 1));
        SetPositionMap.setPositionMap(2, map1, new Position(1, 4));
        SetPositionMap.setPositionMap(1, map1, new Position(5, 4));
        SetPositionMap.setPositionMap(1, map1, new Position(1, 5));
        SetPositionMap.setPositionMap(2, map1, new Position(9, 5));
        SetPositionMap.setPositionMap(4, map1, new Position(7, 6));
        SetPositionMap.setPositionMap(1, map1, new Position(3, 8));
        SetPositionMap.setPositionMap(3, map1, new Position(6, 7));
        SetPositionMap.setPositionMap(1, map1, new Position(1, 9));
        SetPositionMap.setPositionMap(3, map1, new Position(9, 9));
        date1.sideLength(9);
        date1.addNumbers(map1);
        date1.neighbours(new Position(7, 6), 8);
        SetPositionMap.setPositionMap(2, map2, new Position(4, 4));
        SetPositionMap.setPositionMap(1, map2, new Position(5, 4));
        SetPositionMap.setPositionMap(4, map2, new Position(6, 5));
        SetPositionMap.setPositionMap(1, map2, new Position(5, 6));
        SetPositionMap.setPositionMap(4, map2, new Position(7, 6));
        SetPositionMap.setPositionMap(1, map2, new Position(3, 7));
        SetPositionMap.setPositionMap(3, map2, new Position(6, 7));
        date2.sideLength(9);
        date2.addNumbers(map2);
        date2.neighbours(new Position(5, 5), 8);
    }
}
