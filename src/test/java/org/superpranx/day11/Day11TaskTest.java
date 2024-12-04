package org.superpranx.day11;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day11TaskTest extends TestCase {

  public Day11TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day11TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day11Task.partOne(Day11Task.extractListsFromInputFile("day11/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day11Task.partTwo(Day11Task.extractListsFromInputFile("day11/input.txt")));
  }
}
