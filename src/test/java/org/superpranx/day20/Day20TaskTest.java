package org.superpranx.day20;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day20TaskTest extends TestCase {

  public Day20TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day20TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day20Task.partOne(Day20Task.extractListsFromInputFile("day20/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day20Task.partTwo(Day20Task.extractListsFromInputFile("day20/input.txt")));
  }
}
