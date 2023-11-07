
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.setAll();
        Indiana newIndiana = new Indiana();
        newIndiana.setPlayerController(controller);
        newIndiana.underwaterMovesAllowed(5);
        newIndiana.findExit();
        Position currentPosition = new Position(2, 4);
        Direction directionNorth = Direction.NORTH;
        Direction directionEast = Direction.EAST;
        Direction directionSouth = Direction.SOUTH;
        Direction directionWest = Direction.WEST;
    }
}