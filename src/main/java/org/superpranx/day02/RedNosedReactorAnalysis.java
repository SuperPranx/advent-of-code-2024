package org.superpranx.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.superpranx.util.FileReaderUtil;

public class RedNosedReactorAnalysis {

  private RedNosedReactorAnalysis() {
  }

  public static int checkReportsForSafety(List<List<Integer>> reactorReports) {
    return reactorReports.stream()
        .map(RedNosedReactorAnalysis::checkSafety)
        .map(isSafe -> isSafe ? 1 : 0)
        .reduce(0, Integer::sum);
  }

  private static boolean checkSafety(List<Integer> report) {
    if (Objects.equals(report.get(0), report.get(1))) {
      return false;
    }

    boolean isIncreasing = report.get(0) < report.get(1);
    boolean isSafe = true;

    for (int i = 1; i < report.size(); i++) {
      int absDiff = Math.abs(report.get(i) - report.get(i - 1));
      if (absDiff < 1 || absDiff > 3) {
        isSafe = false;
        break;
      }
      if (isIncreasing && report.get(i) <= report.get(i - 1)) {
        isSafe = false;
        break;
      } else if (!isIncreasing && report.get(i) >= report.get(i - 1)) {
        isSafe = false;
        break;
      }
    }

    return isSafe;
  }

  public static int checkReportsForSafetyWithTolerance(List<List<Integer>> reactorReports) {
    return reactorReports.stream()
        .map(report -> checkSafety(report) || checkReducedReport(report))
        .map(isSafe -> isSafe ? 1 : 0)
        .reduce(0, Integer::sum);
  }

  private static boolean checkReducedReport(List<Integer> report) {
    for (int i = 0; i < report.size(); i++) {
      List<Integer> reducedList = new ArrayList<>(report);
      reducedList.remove(i);
      if (checkSafety(reducedList)) {
        return true;
      }
    }
    return false;
  }

  public static List<List<Integer>> extractListsFromInputFile(String inputFilePath) {
    List<List<Integer>> reactorReports = new ArrayList<>();
    FileReaderUtil.getListOfLines(inputFilePath)
        .stream()
        .filter(StringUtils::isNotBlank)
        .forEach(line -> reactorReports.add(Arrays.stream(line.split(" ")).map(Integer::parseInt).toList()));
    return reactorReports;
  }
}
