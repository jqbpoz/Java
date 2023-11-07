import java.util.HashSet;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;

//Należy dbać zby blind pozycja wykonywała to co pozycja kontrolera
public class Indiana implements Explorer {
    int moves;
    PlayerController controller;


    public Direction opositeTo(Direction direction) {
        switch (direction) {
            case NORTH -> {
                return Direction.SOUTH;
            }
            case EAST -> {
                return Direction.WEST;
            }
            case SOUTH -> {
                return Direction.NORTH;
            }
            case WEST -> {
                return Direction.EAST;
            }
        }
        return direction;
    }

    public Direction toLeft(Direction direction) {
        switch (direction) {
            case NORTH -> {
                return Direction.WEST;
            }
            case EAST -> {
                return Direction.NORTH;
            }
            case SOUTH -> {
                return Direction.EAST;
            }
            case WEST -> {
                return Direction.SOUTH;
            }
        }
        return direction;
    }

    Position blindPosition = new Position(0, 0);

    Set<Position> floodedBlindSet = new HashSet<>();
    Set<Position> FireAndWallBlindSet = new HashSet<>();
    Set<Position> whitePathSet = new HashSet<>();


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
        boolean loop = true;
        //kierunkami sterować będziemy w zaleności od potrzeby

        Direction currentDirection = Direction.EAST;
        Position futureBlindPosition = null;
        while (loop) {
            System.out.println("moja ślepa pozycja" + blindPosition);
            System.out.println();
            try {
                futureBlindPosition = currentDirection.step(blindPosition);
                if (FireAndWallBlindSet.contains(futureBlindPosition)) {
                    //tutaj w takim przypadku zmienimy kierunek i spóbujemy jeszcze raz damy continue
                    break; // tutaj nie wykonamy tego kroku bo napotkamy na ścianę
                }
                controller.move(currentDirection);
            } catch (OnFire onFireException) {
                currentDirection = opositeTo(currentDirection);
                continue;
            } catch (Flooded floodedException) {
                continue;
            } catch (Wall wallException) {
                System.out.println("Gracz natrafił na ścianę! ");
                FireAndWallBlindSet.add(futureBlindPosition);
                System.out.println("Do zbioru ścian dodano:" + futureBlindPosition);
                //nie zmieniamy
                continue;
            } catch (Exit exitException) {
                System.out.println("Gracz znalazł wyjście! ");
                loop = false;
                continue;
            }
            blindPosition = futureBlindPosition;
            whitePathSet.add(blindPosition);

        }

    }
}
