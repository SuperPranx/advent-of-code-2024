package org.superpranx.day03;

import java.util.Arrays;

import org.superpranx.util.FileReaderUtil;

public class TobogganRentalMemoryExtractor {

  private TobogganRentalMemoryExtractor() {
  }

  public static int sumValidMuls(String input) {
    // 1. Find all substrings starting with "mul("
    // 2. Filter out the incorrect ones and only leave ones that have two numbers separated by a comma
    // 3. Take only the numbers, discard the rest
    // 4. Convert, multiply and add to the total sum
    return Arrays.stream(input.split("mul\\("))
        .filter(part -> part.matches("^\\d{1,3},\\d{1,3}\\).*$"))
        .map(part -> part.split("\\)")[0]).map(numberPair -> {
          String[] numberStrings = numberPair.split(",");
          return Integer.parseInt(numberStrings[0]) * Integer.parseInt(numberStrings[1]);
        }).reduce(0, Integer::sum);
  }

  public static int sumExcludingDeactivatedSections(String input) {
    // 1. Find all substrings starting with "do()", assuming there is an implicit "do()" at the start
    // 2. Discard the part of the substrings starting with "don't()"
    // 3. Use the already created sum function to calculate the sums of the remaining muls
    // 4. Sum them up
    return Arrays.stream(input.split("do\\(\\)"))
        .map(part -> part.replaceFirst("don't\\(\\).*$", ""))
        .map(TobogganRentalMemoryExtractor::sumValidMuls)
        .reduce(0, Integer::sum);
  }

  public static String extractInputFrom(String inputFilePath) {
    return String.join("", FileReaderUtil.getListOfLines(inputFilePath));
  }
}
