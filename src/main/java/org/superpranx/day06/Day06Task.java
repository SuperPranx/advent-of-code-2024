package org.superpranx.day06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.Position;

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
  private static final List<MazePosition> ALL_WALLS = new ArrayList<>();
  private static final Map<Integer, List<MazePosition>> WALLS_PER_ROW = new HashMap<>();
  private static final Map<Integer, List<MazePosition>> WALLS_PER_COLUMN = new HashMap<>();

  private Day06Task() {
  }

  public static void clear() {
    isInitComplete = false;
    startingPosition = new MazePosition(0, 0);
    WALLS_PER_ROW.clear();
    WALLS_PER_COLUMN.clear();
  }

  public static int partOne() {
    assert isInitComplete;
    Movement currentMove = Movement.UP;
    List<MazePosition> guardStops = new ArrayList<>();
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
    return 123;
  }

  public static int partTwo() {
    return 456;
  }

  public static void extractMazeDataFromInputFile(String inputFilePath) {
    List<String> rows = FileReaderUtil.getListOfLines(inputFilePath);
    maxH = rows.get(0).length() - 1;
    maxV = rows.size() - 1;
    for (int v = 0; v < rows.size(); v++) {
      if (StringUtils.isBlank(rows.get(v))) {
        continue;
      }
      if (rows.get(v).contains(GUARD)) {
        startingPosition = new MazePosition(rows.get(v).indexOf(GUARD), v);
      }
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
        nextPosition = nextValidStop.positionAfterMove(move);
      }
      return nextValidStop;
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
      return switch(this) {
        case UP -> RIGHT;
        case RIGHT -> DOWN;
        case DOWN -> LEFT;
        case LEFT -> UP;
      };
    }

    List<MazePosition> wallsInSameLineAs(MazePosition position) {
      return switch(this) {
        case UP, DOWN -> WALLS_PER_COLUMN.get(position.h);
        case RIGHT, LEFT -> WALLS_PER_ROW.get(position.v);
      };
    }
  }
}
