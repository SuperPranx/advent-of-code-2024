package org.superpranx.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.superpranx.util.FileReaderUtil;

public class FindingXmas {

  private FindingXmas() {
  }

  public static int howManyXmasIn(List<List<String>> puzzleMatrix) {
    int result = 0;
    int sizeX = puzzleMatrix.get(0).size();
    int sizeY = puzzleMatrix.size();
    List<String> otherLetters = List.of("M", "A", "S");
    for (int y = 0; y < sizeY; y++) {
      List<String> currentRow = puzzleMatrix.get(y);
      for (int x = 0; x < sizeX; x++) {
        if (StringUtils.equals("X", currentRow.get(x))) {
          result += checkRemainingLetters(
              puzzleMatrix,
              new Instructions(x, y, 0, 1, sizeX, sizeY),
              new ArrayList<>(otherLetters));
          result += checkRemainingLetters(
              puzzleMatrix,
              new Instructions(x, y, 0, -1, sizeX, sizeY),
              new ArrayList<>(otherLetters));
          result += checkRemainingLetters(
              puzzleMatrix,
              new Instructions(x, y, 1, 0, sizeX, sizeY),
              new ArrayList<>(otherLetters));
          result += checkRemainingLetters(
              puzzleMatrix,
              new Instructions(x, y, -1, 0, sizeX, sizeY),
              new ArrayList<>(otherLetters));
          result += checkRemainingLetters(
              puzzleMatrix,
              new Instructions(x, y, 1, 1, sizeX, sizeY),
              new ArrayList<>(otherLetters));
          result += checkRemainingLetters(
              puzzleMatrix,
              new Instructions(x, y, -1, 1, sizeX, sizeY),
              new ArrayList<>(otherLetters));
          result += checkRemainingLetters(
              puzzleMatrix,
              new Instructions(x, y, 1, -1, sizeX, sizeY),
              new ArrayList<>(otherLetters));
          result += checkRemainingLetters(
              puzzleMatrix,
              new Instructions(x, y, -1, -1, sizeX, sizeY),
              new ArrayList<>(otherLetters));
        }
      }
    }
    return result;
  }

  private static int checkRemainingLetters(
      List<List<String>> puzzle,
      Instructions data,
      List<String> remainingLetters) {
    int newX = data.x + data.dirX;
    int newY = data.y + data.dirY;
    if (newX < 0 || newY < 0 || newX >= data.sizeX || newY >= data.sizeY) {
      return 0;
    } else {
      String currentLetter = puzzle.get(newY).get(newX);
      if (!remainingLetters.remove(0).equals(currentLetter)) {
        return 0;
      } else if (remainingLetters.isEmpty()) {
        return 1;
      } else {
        return checkRemainingLetters(puzzle, new Instructions(data, newX, newY), remainingLetters);
      }
    }
  }

  public static int howManyCrossMasIn(List<List<String>> puzzleMatrix) {
    int result = 0;
    int sizeX = puzzleMatrix.get(0).size();
    int sizeY = puzzleMatrix.size();
    List<String> control = List.of("M", "S");

    for (int y = 1; y < sizeY - 1; y++) {
      List<String> currentRow = puzzleMatrix.get(y);
      for (int x = 1; x < sizeX - 1; x++) {
        if (StringUtils.equals("A", currentRow.get(x))) {
          String botR = puzzleMatrix.get(y + 1).get(x + 1);
          String topL = puzzleMatrix.get(y - 1).get(x - 1);
          List<String> backwardDiagonal = List.of(topL, botR);

          String topR = puzzleMatrix.get(y - 1).get(x + 1);
          String botL = puzzleMatrix.get(y + 1).get(x - 1);
          List<String> forwardDiagonal = List.of(botL, topR);

          if (backwardDiagonal.containsAll(control) && forwardDiagonal.containsAll(control)) {
            result++;
          }
        }
      }
    }
    return result;
  }

  public static List<List<String>> extractListsFromInputFile(String inputFilePath) {
    return FileReaderUtil.getListOfLines(inputFilePath)
        .stream()
        .map(line -> line.split(""))
        .map(Arrays::asList)
        .toList();
  }

  private record Instructions(int x, int y, int dirX, int dirY, int sizeX, int sizeY) {

    public Instructions(Instructions data, int newX, int newY) {
      this(newX, newY, data.dirX, data.dirY, data.sizeX, data.sizeY);
    }
  }
}
