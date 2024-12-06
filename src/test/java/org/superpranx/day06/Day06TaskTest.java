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
    assertEquals(123, Day06Task.partOne(Day06Task.extractMazeFromInputFile("day06/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day06Task.partTwo(Day06Task.extractMazeFromInputFile("day06/input.txt")));
  }
}
