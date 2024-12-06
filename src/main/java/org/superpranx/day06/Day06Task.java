package org.superpranx.day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.superpranx.util.FileReaderUtil;

public class Day06Task {

  private Day06Task() {
  }

  public static int partOne(List<List<String>> maze) {
    int v = -1;
    int h = -1;
    for (int i = 0; i < maze.size(); i++) {
      for (int j = 0; j < maze.get(i).size(); j++) {
        if (maze.get(i).get(j).equals("^")) {
          v = i;
          h = j;
          maze.get(i).set(j, "X");
          break;
        }
      }
    }

    boolean isGuardHere = true;
    int steps = 1;
    int dirH = 0;
    int dirV = -1;
    while (isGuardHere) {
      
    }
  }

  public static int partTwo(List<List<String>> maze) {
    return 456;
  }

  public static List<List<String>> extractMazeFromInputFile(String inputFilePath) {
    return new ArrayList<>(FileReaderUtil.getListOfLines(inputFilePath)
        .stream()
        .map(line -> new ArrayList<>(Arrays.stream(line.split("")).toList()))
        .collect(Collectors.toList()));
  }
}
