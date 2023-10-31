import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SetPositionMap {
    public static void setPositionMap(int key, Map<Integer, Set<Position>> map, Position position) {
        if (!map.containsKey(key)) {
            map.put(key, new HashSet<>());
        }
        map.get(key).add(position);
    }
}
