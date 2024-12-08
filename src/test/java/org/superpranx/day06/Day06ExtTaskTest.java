package org.superpranx.day06;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day06ExtTaskTest extends TestCase {

  public Day06ExtTaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day06ExtTaskTest.class);
  }

  public void testPartTwo() {
    assertEquals(1443, Day06ExtTask.solvePart2(Day06ExtTask.extractListsFromInputFile("day06/input.txt")));
  }
}
