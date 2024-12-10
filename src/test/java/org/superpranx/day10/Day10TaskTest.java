package org.superpranx.day10;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day10TaskTest extends TestCase {

  public Day10TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day10TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(548, Day10Task.partOne(Day10Task.extractListsFromInputFile("day10/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(1252, Day10Task.partTwo(Day10Task.extractListsFromInputFile("day10/input.txt")));
  }
}
