// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
      Controller controller = new Controller();
      Indiana newIndiana = new Indiana();
      newIndiana.setPlayerController(controller);
      newIndiana.underwaterMovesAllowed(5);
      newIndiana.findExit();
      Position currentPosition = new Position(2,4);
      Direction directionNorth = Direction.NORTH;
      Direction directionEast = Direction.EAST;
      Direction directionSouth = Direction.SOUTH;
      Direction directionWest = Direction.WEST;



    }
}