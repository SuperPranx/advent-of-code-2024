package org.superpranx.day19;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day19TaskTest extends TestCase {

  public Day19TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day19TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day19Task.partOne(Day19Task.extractListsFromInputFile("day19/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day19Task.partTwo(Day19Task.extractListsFromInputFile("day19/input.txt")));
  }
}
