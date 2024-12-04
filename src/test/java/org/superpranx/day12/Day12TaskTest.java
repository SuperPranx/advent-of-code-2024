package org.superpranx.day12;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day12TaskTest extends TestCase {

  public Day12TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day12TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day12Task.partOne(Day12Task.extractListsFromInputFile("day12/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day12Task.partTwo(Day12Task.extractListsFromInputFile("day12/input.txt")));
  }
}
