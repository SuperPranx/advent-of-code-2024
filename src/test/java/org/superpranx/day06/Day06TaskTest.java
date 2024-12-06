package org.superpranx.day06;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day06TaskTest extends TestCase {

  public Day06TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day06TaskTest.class);
  }

  public void testPartOne() {
    Day06Task.extractMazeDataFromInputFile("day06/input.txt");
    assertEquals(123, Day06Task.partOne());
  }

  public void testPartTwo() {
    Day06Task.extractMazeDataFromInputFile("day06/input.txt");
    assertEquals(456, Day06Task.partTwo());
  }
}
