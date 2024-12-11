package org.superpranx.day11;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.superpranx.util.FileReaderUtil;

public class Day11Task {

  private static final BigInteger sum = BigInteger.ZERO;

  private Day11Task() {
  }

  public static int partOne(List<BigInteger> stones, int times) {
    int count = times;
    BigInteger multiplier = new BigInteger("2024");
    List<BigInteger> oldStones = stones;
    while (count-- > 0) {
      List<BigInteger> newStones = new ArrayList<>();
      oldStones.stream().forEach(stone -> {
        String stoneNumber = stone.toString();
        int stoneNumberLength = stoneNumber.length();
        if (BigInteger.ZERO.compareTo(stone) == 0) {
          newStones.add(BigInteger.ONE);
        } else if (stoneNumberLength % 2 == 0) {
          int half = stoneNumberLength >> 1;
          newStones.add(new BigInteger(stoneNumber.substring(0, half)));
          newStones.add(new BigInteger(stoneNumber.substring(half)));
        } else {
          newStones.add(stone.multiply(multiplier));
        }
      });
      oldStones = newStones;
    }
    return oldStones.size();
  }

  public static List<BigInteger> extractListsFromInputFile(String inputFilePath) {
    return Arrays.stream(
        FileReaderUtil.getListOfLines(inputFilePath).get(0).split(" "))
        .map(BigInteger::new).toList();
  }
}
