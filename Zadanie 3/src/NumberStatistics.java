import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class NumberStatistics implements Statistics {
    int lenght;
    Map<Integer, Set<Position>> numberPositions;

    public int squareDistance(Position position, Position posToCompare) {
        int xDistance = Math.abs(position.col() - posToCompare.col());
        int yDistance = Math.abs(position.row() - posToCompare.row());
        if ((xDistance > (this.lenght / 2))) {
            xDistance = this.lenght - xDistance;
        }
        if ((yDistance > (this.lenght / 2))) {
            yDistance = this.lenght - yDistance;
        }
        return xDistance * xDistance + yDistance * yDistance;
    }

    public static void mainMapPutter(Map<Integer, Map<Integer, Integer>> mainMap, int mainMapKey, Map<Integer, Integer> counterMap) {
        if (!mainMap.containsKey(mainMapKey)) {
            mainMap.put(mainMapKey, new HashMap<>());
        }
        mainMap.put(mainMapKey, counterMap);
    }

    public static void counterMapPutter(Map<Integer, Integer> temporaryMap, int distanceKey) {
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
        this.numberPositions = numberPositions;
    }

    @Override
    public Map<Integer, Map<Integer, Integer>> neighbours(Position position, int maxDistanceSquared) {
        Map<Integer, Map<Integer, Integer>> neighboursMap = new HashMap<>();
        for (Map.Entry<Integer, Set<Position>> entry : this.numberPositions.entrySet()) {
            int mainMapKey = entry.getKey();
            Map<Integer, Integer> counterMap = new HashMap<>();
            Set<Position> positions = entry.getValue();
            for (Position posToCompare : positions) {
                int distanceKey = squareDistance(position, posToCompare);
                if (distanceKey <= maxDistanceSquared && distanceKey > 0) {
                    counterMapPutter(counterMap, distanceKey);
                }
            }
            if (!counterMap.isEmpty()) {
                mainMapPutter(neighboursMap, mainMapKey, counterMap);
            }
        }
        return neighboursMap;
    }
}
