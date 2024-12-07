package org.superpranx.day06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.superpranx.util.FileReaderUtil;

public class Day06Task {

  private static final String WALL = "#";
  private static final String EMPTY = ".";
  private static final String GUARD = "^";

  private static int maxH = 0;
  private static int maxV = 0;
  private static boolean isInitComplete = false;
  private static MazePosition startingPosition = new MazePosition(0, 0);
  private static List<MazePosition> guardStops = new ArrayList<>();
  private static final HashSet<MazePosition> ALL_WALLS = new HashSet<>();
  private static final Map<Integer, List<MazePosition>> WALLS_PER_ROW = new HashMap<>();
  private static final Map<Integer, List<MazePosition>> WALLS_PER_COLUMN = new HashMap<>();

  private static HashSet<MazePosition> tilesSteppedOn = new HashSet<>(100000);
  private static List<MazePosition> pathTaken = new ArrayList<>();

  private Day06Task() {
  }

  public static void clear() {
    isInitComplete = false;
    WALLS_PER_ROW.clear();
    WALLS_PER_COLUMN.clear();
    tilesSteppedOn.clear();
    pathTaken.clear();
    guardStops.clear();
    startingPosition = new MazePosition(0, 0);
  }

  public static int partOne() {
    assert isInitComplete;
    Movement currentMove = Movement.UP;
    guardStops.add(startingPosition);
    MazePosition currentPosition = startingPosition;
    MazePosition nextPosition;
    do {
      nextPosition = currentPosition.nextStopInDirection(currentMove);
      currentMove = currentMove.nextDirection();
      if (nextPosition.equals(currentPosition)) {
        continue;
      }
      currentPosition = nextPosition;
      guardStops.add(currentPosition);
    } while (!nextPosition.isExitPoint());
    isInitComplete = false;
    return tilesSteppedOn.size();
  }

  public static int partTwo() {
    partOne();
    int validLoops = 0;
    List<PathMovement> paths = new ArrayList<>();
    HashSet<PathMovement> generatedPaths = new HashSet<>();
    HashSet<MazePosition> newObstacles = new HashSet<>();
    Movement pathDirection = Movement.UP;
    for (int i = 0; i < guardStops.size() - 1; i++, pathDirection = pathDirection.nextDirection()) {
      MazePosition currentPosition = guardStops.get(i);
      MazePosition nextPosition = guardStops.get(i + 1);
      MazePosition extendedStart = currentPosition.nextStopInDirectionWithoutHistory(pathDirection.reverse());
      PathMovement newPathLine = new PathMovement(extendedStart, nextPosition, pathDirection);

      // fill in paths with all wall projections
      if (!generatedPaths.contains(newPathLine)) {
        generatedPaths.add(newPathLine);
        MazePosition projector = newPathLine.start;
        MazePosition projectorEnd = newPathLine.end.positionAfterMove(pathDirection);
        Movement wallDirection = pathDirection.previousDirection();
        while (projector != projectorEnd) {
          if (ALL_WALLS.contains(projector.positionAfterMove(wallDirection))) {
            PathMovement projection = new PathMovement(
                projector.nextStopInDirectionWithoutHistory(wallDirection.reverse()),
                projector,
                wallDirection);
            generatedPaths.add(projection);
            paths.add(projection);
          }
          projector = projector.nextStopInDirectionWithoutHistory(pathDirection);
        }
      }

      // find how many intersections there are
      paths.stream()
          .filter(path -> path.direction.equals(newPathLine.direction.nextDirection()))
          .filter(path -> path.intersects(newPathLine)).forEach(path -> {
            MazePosition newObstacle = path.intersection(newPathLine).positionAfterMove(newPathLine.direction);
            if (!newObstacle.equals(startingPosition) && !ALL_WALLS.contains(newObstacle)) {
              newObstacles.add(newObstacle);
            }
          });

      paths.add(newPathLine);
    }

    return newObstacles.size();
  }

  public static void extractMazeDataFromInputFile(String inputFilePath) {
    List<String> rows = FileReaderUtil.getListOfLines(inputFilePath);
    maxH = rows.get(0).length() - 1;
    maxV = rows.size() - 1;
    for (int v = 0; v < rows.size(); v++) {
      if (StringUtils.isBlank(rows.get(v))) {
        continue;
      }
      //      if (rows.get(v).contains(GUARD)) {
      //        startingPosition = new MazePosition(rows.get(v).indexOf(GUARD), v);
      //      }
      List<String> cells = List.of(rows.get(v).split(""));
      for (int h = 0; h < cells.size(); h++) {
        switch (cells.get(h)) {
          case WALL -> {
            MazePosition wallCoordinates = new MazePosition(h, v);
            ALL_WALLS.add(wallCoordinates);

            List<MazePosition> wallsForRow = WALLS_PER_ROW.getOrDefault(v, new ArrayList<>());
            wallsForRow.add(wallCoordinates);
            WALLS_PER_ROW.put(v, wallsForRow);

            List<MazePosition> wallsForColumn = WALLS_PER_COLUMN.getOrDefault(h, new ArrayList<>());
            wallsForColumn.add(wallCoordinates);
            WALLS_PER_COLUMN.put(h, wallsForColumn);
          }
          case GUARD -> startingPosition = new MazePosition(h, v);
        }
      }
    }
    tilesSteppedOn.add(startingPosition);
    pathTaken.add(startingPosition);
    isInitComplete = true;
  }

  private record MazePosition(int h, int v) {

    MazePosition positionAfterMove(Movement move) {
      return new MazePosition(h + move.movH, v + move.movV);
    }

    boolean isValid() {
      return h >= 0 && v >= 0 && h <= maxH && v <= maxV;
    }

    boolean isExitPoint() {
      return h == 0 || v == 0 || h == maxH || v == maxV;
    }

    MazePosition nextStopInDirection(Movement move) {
      List<MazePosition> wallPositionsInLine = move.wallsInSameLineAs(this);
      MazePosition nextValidStop = this;
      MazePosition nextPosition = nextValidStop;
      while (nextPosition.isValid() && !wallPositionsInLine.contains(nextPosition)) {
        nextValidStop = nextPosition;
        tilesSteppedOn.add(nextValidStop);
        pathTaken.add(nextValidStop);
        nextPosition = nextValidStop.positionAfterMove(move);
      }
      return nextValidStop;
    }

    MazePosition nextStopInDirectionWithoutHistory(Movement move) {
      List<MazePosition> wallPositionsInLine = move.wallsInSameLineAs(this);
      MazePosition nextValidStop = this;
      MazePosition nextPosition = nextValidStop;
      while (nextPosition.isValid() && !wallPositionsInLine.contains(nextPosition)) {
        nextValidStop = nextPosition;
        nextPosition = nextValidStop.positionAfterMove(move);
      }
      return nextValidStop;
    }
  }

  private record PathMovement(MazePosition start, MazePosition end, Movement direction) {

    public boolean intersects(PathMovement secondPath) {
      if (direction.isSameOrientationAs(secondPath.direction)) {
        return false;
      } else {
        PathMovement horizontal = direction.isHorizontal() ? reorientLtRTtD(this) : reorientLtRTtD(secondPath);
        PathMovement vertical = direction.isVertical() ? reorientLtRTtD(this) : reorientLtRTtD(secondPath);
        return horizontal.start.h < vertical.start.h
            && horizontal.end.h > vertical.start.h
            && vertical.start.v < horizontal.start.v
            && vertical.end.v > horizontal.start.v;
      }
    }

    public MazePosition intersection(PathMovement secondPath) {
      PathMovement horizontal = direction.isHorizontal() ? reorientLtRTtD(this) : reorientLtRTtD(secondPath);
      PathMovement vertical = direction.isVertical() ? reorientLtRTtD(this) : reorientLtRTtD(secondPath);
      return new MazePosition(vertical.start.h, horizontal.start.v);
    }

    public PathMovement reorientLtRTtD(PathMovement path) {
      if (path.direction.isVertical()) {
        return path.start.h < path.end.h
            ? new PathMovement(path.start, path.end, path.direction)
            : new PathMovement(path.end, path.start, path.direction);
      } else {
        return path.start.v < path.end.v
            ? new PathMovement(path.start, path.end, path.direction)
            : new PathMovement(path.end, path.start, path.direction);
      }
    }
  }

  private enum Movement {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    final int movH;
    final int movV;

    Movement(int movH, int movV) {
      this.movH = movH;
      this.movV = movV;
    }

    Movement nextDirection() {
      return switch (this) {
        case UP -> RIGHT;
        case RIGHT -> DOWN;
        case DOWN -> LEFT;
        case LEFT -> UP;
      };
    }

    Movement previousDirection() {
      return switch (this) {
        case UP -> LEFT;
        case RIGHT -> UP;
        case DOWN -> RIGHT;
        case LEFT -> DOWN;
      };
    }

    Movement reverse() {
      return nextDirection().nextDirection();
    }

    List<MazePosition> wallsInSameLineAs(MazePosition position) {
      return switch (this) {
        case UP, DOWN -> WALLS_PER_COLUMN.get(position.h);
        case RIGHT, LEFT -> WALLS_PER_ROW.get(position.v);
      };
    }

    boolean isVertical() {
      return this == UP || this == DOWN;
    }

    boolean isHorizontal() {
      return this == RIGHT || this == LEFT;
    }

    boolean isSameOrientationAs(Movement movement) {
      return (this.isVertical() && movement.isVertical()) || (this.isHorizontal() && movement.isHorizontal());
    }
  }
}
