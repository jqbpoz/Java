import java.util.*;

class Indiana implements Explorer {
    int moves;
    PlayerController controller;
    List<Direction> directions = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
    Position blindPosition = new Position(0, 0);
    Set<Position> FireAndWallBlindSet = new HashSet<>();

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

    public Direction toRight(Direction direction) {
        switch (direction) {
            case NORTH -> {
                return Direction.EAST;
            }
            case EAST -> {
                return Direction.SOUTH;
            }
            case SOUTH -> {
                return Direction.WEST;
            }
            case WEST -> {
                return Direction.NORTH;
            }
        }
        return direction;
    }

    @Override
    public void underwaterMovesAllowed(int moves) {
        this.moves = moves;
    }

    @Override
    public void setPlayerController(PlayerController controller) {
        this.controller = controller;
    }


    public boolean seachPath(Position position, Set<Position> path) throws OnFire, Flooded, Wall, Exit {
        Direction direction;
        for (int i = 0; i < 4; i++) {
            direction = directions.get(i);
            System.out.println(direction);
            Position nextstep = direction.step(position);
            if (path.contains(nextstep) || FireAndWallBlindSet.contains(nextstep)) {
                continue;
            }
            try {
                controller.move(direction);
            } catch (OnFire e) {
                FireAndWallBlindSet.add(nextstep);
                controller.move(opositeTo(direction));
                path.remove(nextstep);
                continue;
            } catch (Flooded e) {
                FireAndWallBlindSet.add(toRight(direction).step(nextstep));
                FireAndWallBlindSet.add(toLeft(direction).step(nextstep));
            } catch (Wall e) {
                FireAndWallBlindSet.add(nextstep);
                continue;
            } catch (Exit e) {
                return true;
            }
            path.add(position);
            if (seachPath(nextstep, path)) {
                return true;
            }
            controller.move(opositeTo(direction));
            path.remove(nextstep);
        }
        return false;
    }

    @Override
    public void findExit() {
        Set<Position> path = new HashSet<>();
        path.add(blindPosition);
        try {
            seachPath(blindPosition, path);
        } catch (OnFire | Flooded | Wall | Exit e) {
            throw new RuntimeException(e);
        }
    }
}
