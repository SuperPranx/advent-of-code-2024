package org.superpranx.day05;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day05TaskTest extends TestCase {

  public Day05TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day05TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(4609,
        Day05Task.partOne(
            Day05Task.extractPositionData("day05/input01.txt"),
            Day05Task.extractPageUpdates("day05/input02.txt")));
  }

  public void testPartTwo() {
    assertEquals(5723,
        Day05Task.partTwo(
            Day05Task.extractPositionData("day05/input01.txt"),
            Day05Task.extractPageUpdates("day05/input02.txt")));
  }
}
