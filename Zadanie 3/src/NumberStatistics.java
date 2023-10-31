import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NumberStatistics implements Statistics {
    int lenght;
    Map<Integer, Set<Position>> map;

    //Funtion return squareDistance beetween two position based on periodic conditions
    public int squareDistance(Position position, Position posToCompare) {
        int xDistance = Math.abs(position.col() - posToCompare.col());
        int yDistance = Math.abs(position.row() - posToCompare.row());
        if ((xDistance > (this.lenght / 2))) {
            xDistance = this.lenght - xDistance;
        }
        if ((yDistance > (this.lenght / 2))) {
            yDistance = this.lenght - yDistance;
        }
        System.out.println(position);
        System.out.println(posToCompare);
        System.out.println(position.col() + " " + posToCompare.col() + " " + xDistance);
        System.out.println(position.row() + " " + posToCompare.row() + " " + yDistance);
        return xDistance * xDistance + yDistance * yDistance;
    }

    public static void addValueMapToMainMap(Map<Integer, Map<Integer, Integer>> mainMap, int key, Map<Integer, Integer> valueMap) {
        if (!mainMap.containsKey(key)) {
            mainMap.put(key, new HashMap<>());
        }
        mainMap.put(key, valueMap);
    }

    public static void valueMapPutter(Map<Integer, Integer> temporaryMap, int distanceKey) {
        if (!temporaryMap.containsKey(distanceKey)) {
            temporaryMap.put(distanceKey, 1);
        } else {
            temporaryMap.merge(distanceKey, 1, Integer::sum);
        }
    }

    @Override
    public void sideLength(int length) {
        this.lenght = length;
    }


    @Override
    public void addNumbers(Map<Integer, Set<Position>> numberPositions) {
        this.map = numberPositions;
    }

    @Override
    public Map<Integer, Map<Integer, Integer>> neighbours(Position position, int maxDistanceSquared) {
        Map<Integer, Map<Integer, Integer>> neighboursMap = new HashMap<>();
        // key=squere , value=howMuch
        for (Map.Entry<Integer, Set<Position>> entry : this.map.entrySet()) {
            int mainKey = entry.getKey();
            Map<Integer, Integer> valueMap = new HashMap<>();
            Set<Position> positions = entry.getValue();
            for (Position posToCompare : positions) {
                int distanceKey = squareDistance(position, posToCompare);
                System.out.println(distanceKey);
                if (distanceKey <= maxDistanceSquared && distanceKey > 0) {
                    valueMapPutter(valueMap, distanceKey);
                    System.out.println(valueMap);
                }
            }
            addValueMapToMainMap(neighboursMap, mainKey, valueMap);
        }
        System.out.println(neighboursMap);
        return neighboursMap;
    }
}
