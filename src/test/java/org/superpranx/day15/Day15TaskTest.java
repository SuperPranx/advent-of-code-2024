package org.superpranx.day15;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day15TaskTest extends TestCase {

  public Day15TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day15TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day15Task.partOne(Day15Task.extractListsFromInputFile("day15/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day15Task.partTwo(Day15Task.extractListsFromInputFile("day15/input.txt")));
  }
}
