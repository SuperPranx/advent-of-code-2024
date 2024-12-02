package org.superpranx.day03;

import java.util.ArrayList;
import java.util.List;

import org.superpranx.util.FileReaderUtil;

public class Day03Task {

  private Day03Task() {
  }

  public static int partOne(List<String> lines) {
    return 123;
  }

  public static int partTwo(List<String> lines) {
    return 456;
  }

  public static List<String> extractListsFromInputFile(String inputFilePath) {
    List<String> lines = new ArrayList<>();
    lines = FileReaderUtil.getListOfLines(inputFilePath);
    return lines;
  }
}
