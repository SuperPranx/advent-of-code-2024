package org.superpranx.day18;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day18TaskTest extends TestCase {

  public Day18TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day18TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day18Task.partOne(Day18Task.extractListsFromInputFile("day18/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day18Task.partTwo(Day18Task.extractListsFromInputFile("day18/input.txt")));
  }
}
