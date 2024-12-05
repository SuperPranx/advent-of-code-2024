package org.superpranx.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.superpranx.util.FileReaderUtil;

public class Day05Task {

  private Day05Task() {
  }

  public static int partOne(PagePositionRules positionRules, List<List<Integer>> pageUpdates) {
    return findSumOfMiddlePages(positionRules, pageUpdates, new ArrayList<>());
  }

  private static int findSumOfMiddlePages(PagePositionRules positionRules, List<List<Integer>> pageUpdates, List<List<Integer>> incorrectUpdates) {
    return pageUpdates.stream().map(updates -> {
      List<Integer> processedPages = new ArrayList<>();
      List<Integer> remainingPages = new ArrayList<>(updates);
      boolean updateValid = true;
      Integer middlePage = -1;
      do {
        Integer currentPage = remainingPages.remove(0);
        if (containsAny(positionRules.befores.getOrDefault(currentPage, new HashSet<>()), remainingPages)
            || containsAny(positionRules.afters.getOrDefault(currentPage, new HashSet<>()), processedPages)) {
          updateValid = false;
          incorrectUpdates.add(updates);
          break;
        }
        if (processedPages.size() == remainingPages.size()) {
          middlePage = currentPage;
        }
        processedPages.add(currentPage);
      } while (!remainingPages.isEmpty());
      return updateValid ? middlePage : 0;
    }).reduce(0, Integer::sum);
  }

  private static boolean containsAny(HashSet<Integer> setToCheck, List<Integer> valuesToCheck) {
    return valuesToCheck.stream().anyMatch(setToCheck::contains);
  }

  public static int partTwo(PagePositionRules positionRules, List<List<Integer>> pageUpdates) {
    List<List<Integer>> incorrectUpdates = new ArrayList<>();
    findSumOfMiddlePages(positionRules, pageUpdates, incorrectUpdates);

    List<List<Integer>> fixedUpdates = fixIncorrectUpdates(positionRules, incorrectUpdates);
    return findSumOfMiddlePages(positionRules, fixedUpdates, new ArrayList<>());
  }

  private static List<List<Integer>> fixIncorrectUpdates(PagePositionRules positionRules, List<List<Integer>> incorrectUpdates) {
    return incorrectUpdates.stream().map(update -> {
      List<Integer> correctedOrdering = new ArrayList<>(update);
      correctedOrdering.sort((pageA, pageB) -> {
        if (Objects.equals(pageA, pageB)
            || (positionRules.befores.get(pageB) == null && positionRules.afters.get(pageA) == null)
            || (!positionRules.befores.get(pageB).contains(pageA) && !positionRules.afters.get(pageA).contains(pageB))
        ) {
          return 0;
        }
        if (positionRules.befores.get(pageB).contains(pageA) || positionRules.afters.get(pageA).contains(pageB)) {
          return -1;
        } else if (positionRules.befores.get(pageA).contains(pageB) || positionRules.afters.get(pageB)
            .contains(pageA)) {
          return 1;
        } else {
          return 0;
        }
      });
      return List.copyOf(correctedOrdering);
    }).toList();
  }

  public static PagePositionRules extractPositionData(String inputFilePath) {
    HashMap<Integer, HashSet<Integer>> befores = new HashMap<>();
    HashMap<Integer, HashSet<Integer>> afters = new HashMap<>();
    FileReaderUtil.getListOfLines(inputFilePath).forEach(line -> {
      int[] positionRule = Arrays.stream(line.split("\\|")).mapToInt(Integer::parseInt).toArray();
      HashSet<Integer> beforeRulesForNumber = befores.getOrDefault(positionRule[1], new HashSet<>());
      HashSet<Integer> afterRulesForNumber = afters.getOrDefault(positionRule[0], new HashSet<>());
      beforeRulesForNumber.add(positionRule[0]);
      befores.put(positionRule[1], beforeRulesForNumber);
      afterRulesForNumber.add(positionRule[1]);
      afters.put(positionRule[0], afterRulesForNumber);
    });
    return new PagePositionRules(befores, afters);
  }

  public static List<List<Integer>> extractPageUpdates(String inputFilePath) {
    return FileReaderUtil.getListOfLines(inputFilePath)
        .stream()
        .map(stringUpdates ->
            Arrays.stream(stringUpdates.split(","))
                .map(Integer::parseInt)
                .toList())
        .filter(updates -> !updates.isEmpty())
        .toList();
  }

  public record PagePositionRules(HashMap<Integer, HashSet<Integer>> befores,
                                  HashMap<Integer, HashSet<Integer>> afters) {

  }
}
