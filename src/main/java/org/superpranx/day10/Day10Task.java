package org.superpranx.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.superpranx.util.FileReaderUtil;

public class Day10Task {

  private static int sizeX;
  private static int sizeY;

  private Day10Task() {
  }

  public static int partOne(int[][] map) {
    return calculateHikingScore(map, false);
  }

  public static int partTwo(int[][] map) {
    return calculateHikingScore(map, true);
  }

  private static int calculateHikingScore(int[][] map, boolean trailsPerPeakCountSeparately) {
    List<TrailNode> startingPoints = new ArrayList<>();
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == 0) {
          startingPoints.add(new TrailNode(i, j));
        }
      }
    }

    int result = 0;
    for (TrailNode start : startingPoints) {
      HashSet<TrailNode> reachedPeaks = new HashSet<>();
      result += trailsPerPeakCountSeparately
          ? calculateRating(map, start, 0)
          : calculateScore(map, start, 0, reachedPeaks);
    }
    return result;
  }

  private static int calculateScore(int[][] map, TrailNode node, int expectedHeight, HashSet<TrailNode> reachedPeaks) {
    if (node.isNotValid() || map[node.x][node.y] != expectedHeight) {
      return 0;
    }
    int height = map[node.x][node.y];
    if (height == 9) {
      if (reachedPeaks.contains(node)) {
        return 0;
      } else {
        reachedPeaks.add(node);
        return 1;
      }
    } else {
      return calculateScore(map, new TrailNode(node.x - 1, node.y), expectedHeight + 1, reachedPeaks)
          + calculateScore(map, new TrailNode(node.x + 1, node.y), expectedHeight + 1, reachedPeaks)
          + calculateScore(map, new TrailNode(node.x, node.y - 1), expectedHeight + 1, reachedPeaks)
          + calculateScore(map, new TrailNode(node.x, node.y + 1), expectedHeight + 1, reachedPeaks);
    }
  }

  private static int calculateRating(int[][] map, TrailNode node, int expectedHeight) {
    if (node.isNotValid() || map[node.x][node.y] != expectedHeight) {
      return 0;
    }
    int height = map[node.x][node.y];
    if (height == 9) {
      return 1;
    } else {
      return calculateRating(map, new TrailNode(node.x - 1, node.y), expectedHeight + 1)
          + calculateRating(map, new TrailNode(node.x + 1, node.y), expectedHeight + 1)
          + calculateRating(map, new TrailNode(node.x, node.y - 1), expectedHeight + 1)
          + calculateRating(map, new TrailNode(node.x, node.y + 1), expectedHeight + 1);
    }
  }

  public static int[][] extractListsFromInputFile(String inputFilePath) {
    List<int[]> lines = FileReaderUtil.getListOfLines(inputFilePath)
        .stream()
        .filter(StringUtils::isNotBlank)
        .map(line -> Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray()).toList();
    int[][] map = new int[lines.size()][];
    lines.toArray(map);
    sizeX = map.length;
    sizeY = map[0].length;
    return map;
  }

  public record TrailNode(int x, int y) {

    boolean isNotValid() {
      return x < 0 || x >= sizeX || y < 0 || y >= sizeY;
    }
  }
}
