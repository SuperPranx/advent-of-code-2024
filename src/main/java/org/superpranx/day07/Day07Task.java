package org.superpranx.day07;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.superpranx.util.FileReaderUtil;

public class Day07Task {

  private Day07Task() {
  }

  public static BigInteger partOne(List<CalculationParameters> calcParams) {
    return calcParams.parallelStream().filter(params -> {
      String ops = "111111111111".substring(0, params.numbers.size());
      int opFlags = Integer.parseInt(ops, 2);
      int[] flagToggles = new int[ops.length()];
      flagToggles[0] = 1;
      for (int i = 1; i < flagToggles.length; i++) {
        flagToggles[i] = flagToggles[i - 1] * 2;
      }
      boolean calculationPossible = false;
      while (opFlags > -1) {
        if (isCalculationPossible(params, opFlags, flagToggles)) {
          calculationPossible = true;
          break;
        }
        opFlags--;
      }
      return calculationPossible;
    }).map(params -> params.expected).reduce(BigInteger.ZERO, BigInteger::add);
  }

  private static boolean isCalculationPossible(
      CalculationParameters calculationParameters,
      int opFlags,
      int[] flagToggles) {
    BigInteger result = calculationParameters.numbers.get(0);
    for (int i = 1; i < calculationParameters.numbers.size(); i++) {
      result = (opFlags & flagToggles[i - 1]) > 0
          ? result.add(calculationParameters.numbers.get(i))
          : result.multiply(calculationParameters.numbers.get(i));
      if (result.compareTo(calculationParameters.expected) > 0) {
        return false;
      }
    }
    return result.compareTo(calculationParameters.expected) == 0;
  }

  public static BigInteger partTwo(List<CalculationParameters> calcParams) {
    return calcParams.parallelStream().filter(params -> {
      String ops = "222222222222".substring(0, params.numbers.size());
      int opFlags = Integer.parseInt(ops, 3);
      boolean calculationPossible = false;
      while (opFlags > -1) {
        if (isCalculationWithConcatenationPossible(params, padOpsUpTo(Integer.toString(opFlags, 3), ops.length()))) {
          calculationPossible = true;
          break;
        }
        opFlags--;
      }
      return calculationPossible;
    }).map(params -> params.expected).reduce(BigInteger.ZERO, BigInteger::add);
  }

  private static String padOpsUpTo(String ops, int length) {
    StringBuilder result = new StringBuilder(ops);
    while (result.length() < length) {
      result.insert(0, "0");
    }
    return result.toString();
  }

  private static boolean isCalculationWithConcatenationPossible(
      CalculationParameters calculationParameters,
      String opFlags) {
    BigInteger result = calculationParameters.numbers.get(0);
    for (int i = 1; i < calculationParameters.numbers.size(); i++) {
      result = switch (opFlags.charAt(i)) {
        case '2' -> new BigInteger(result.toString() + calculationParameters.numbers.get(i).toString());
        case '1' -> result.add(calculationParameters.numbers.get(i));
        case '0' -> result.multiply(calculationParameters.numbers.get(i));
        default -> throw new IllegalArgumentException("Unrecognized operator command!!!");
      };
      if (result.compareTo(calculationParameters.expected) > 0) {
        return false;
      }
    }
    return result.compareTo(calculationParameters.expected) == 0;
  }

  public static List<CalculationParameters> extractListsFromInputFile(String inputFilePath) {
    List<String> calculations = FileReaderUtil.getListOfLines(inputFilePath);
    return calculations.stream().map(calc -> {
      String[] calcParts = calc.split(":");
      return new CalculationParameters(
          new BigInteger(calcParts[0]),
          Arrays.stream(calcParts[1].trim().split(" ")).map(BigInteger::new).toList());
    }).toList();
  }

  public record CalculationParameters(BigInteger expected, List<BigInteger> numbers) {

  }
}
