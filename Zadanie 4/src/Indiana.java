public class Indiana implements Explorer {
    int moves;
    PlayerController controller;

    Direction directionNorth = Direction.NORTH;
    Direction directionEast = Direction.EAST;
    Direction directionSouth = Direction.SOUTH;
    Direction directionWest = Direction.WEST;

    @Override
    public void underwaterMovesAllowed(int moves) {
        this.moves = moves;
    }

    @Override
    public void setPlayerController(PlayerController controller) {
        this.controller = controller;
    }

    @Override
    public void findExit() {
        try {
            controller.move(Direction.NORTH);

        } catch (OnFire onFireException) {
            System.out.println("Gracz jest w ogniu! " + onFireException.getMessage());
        } catch (Flooded floodedException) {
            System.out.println("Gracz jest zalany! " + floodedException.getMessage());
        } catch (Wall wallException) {
            System.out.println("Gracz natrafił na ścianę! " + wallException.getMessage());
        } catch (Exit exitException) {
            System.out.println("Gracz znalazł wyjście! " + exitException.getMessage());
        } catch (Exception e) {
            System.out.println("Inny wyjątek: " + e.getMessage());
        }

    }
}
