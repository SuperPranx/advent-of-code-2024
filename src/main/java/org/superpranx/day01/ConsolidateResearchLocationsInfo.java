package org.superpranx.day01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.superpranx.util.FileReaderUtil;

public class ConsolidateResearchLocationsInfo {

  private ConsolidateResearchLocationsInfo() {
  }

  public static Long calculateSimilarityScore(List<Long> firstGroupLocations, List<Long> secondGroupLocations) {
    if (firstGroupLocations.size() != secondGroupLocations.size()) {
      throw new IllegalArgumentException("The two location lists must be of the same size");
    }

    Map<Long, Integer> numberOccurrences = new HashMap<>();
    secondGroupLocations.forEach(number -> numberOccurrences.put(
        number,
        numberOccurrences.getOrDefault(number, 0) + 1));

    return firstGroupLocations.stream()
        .map(number -> number * numberOccurrences.getOrDefault(number, 0))
        .reduce(0L, Long::sum);
  }

  public static Long calculateDistances(List<Long> firstGroupLocations, List<Long> secondGroupLocations) {
    if (firstGroupLocations.size() != secondGroupLocations.size()) {
      throw new IllegalArgumentException("The two location lists must be of the same size");
    }

    Collections.sort(firstGroupLocations);
    Collections.sort(secondGroupLocations);

    long differenceSum = 0L;
    for (int i = 0; i < firstGroupLocations.size(); ++i) {
      differenceSum += Math.abs(firstGroupLocations.get(i) - secondGroupLocations.get(i));
    }
    return differenceSum;
  }

  public static DistancesInput extractListsFromInputFile(String inputFilePath) {
    List<Long> firstList = new ArrayList<>();
    List<Long> secondList = new ArrayList<>();

    FileReaderUtil.getListOfLines(inputFilePath).forEach(line -> {
      String[] numbers = line.split(" {3}");
      firstList.add(Long.parseLong(numbers[0]));
      secondList.add(Long.parseLong(numbers[1]));
    });

    Collections.sort(firstList);
    Collections.sort(secondList);
    return new DistancesInput(firstList, secondList);
  }

  public record DistancesInput(List<Long> firstGroupLocations, List<Long> secondGroupLocations) {

  }
}
