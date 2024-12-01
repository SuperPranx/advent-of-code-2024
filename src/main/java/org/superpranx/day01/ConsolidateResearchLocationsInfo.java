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

  public static Long calculateSimilarityScore(GroupLocations groupLocations) {
    if (groupLocations.first.size() != groupLocations.second.size()) {
      throw new IllegalArgumentException("The two location lists must be of the same size");
    }

    Map<Long, Integer> numberOccurrences = new HashMap<>();
    groupLocations.second.forEach(number -> numberOccurrences.put(
        number,
        numberOccurrences.getOrDefault(number, 0) + 1));

    return groupLocations.first.stream()
        .map(number -> number * numberOccurrences.getOrDefault(number, 0))
        .reduce(0L, Long::sum);
  }

  public static Long calculateDistances(GroupLocations groupLocations) {
    if (groupLocations.first.size() != groupLocations.second.size()) {
      throw new IllegalArgumentException("The two location lists must be of the same size");
    }

    long differenceSum = 0L;
    for (int i = 0; i < groupLocations.first.size(); ++i) {
      differenceSum += Math.abs(groupLocations.first.get(i) - groupLocations.second.get(i));
    }
    return differenceSum;
  }

  public static GroupLocations extractListsFromInputFile(String inputFilePath) {
    List<Long> firstGroupLocations = new ArrayList<>();
    List<Long> secondGroupLocations = new ArrayList<>();

    FileReaderUtil.getListOfLines(inputFilePath).forEach(line -> {
      String[] numbers = line.split(" {3}");
      firstGroupLocations.add(Long.parseLong(numbers[0]));
      secondGroupLocations.add(Long.parseLong(numbers[1]));
    });

    Collections.sort(firstGroupLocations);
    Collections.sort(secondGroupLocations);
    return new GroupLocations(firstGroupLocations, secondGroupLocations);
  }

  public record GroupLocations(List<Long> first, List<Long> second) {

  }
}
