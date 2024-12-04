package org.superpranx.day17;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day17TaskTest extends TestCase {

  public Day17TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day17TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day17Task.partOne(Day17Task.extractListsFromInputFile("day17/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day17Task.partTwo(Day17Task.extractListsFromInputFile("day17/input.txt")));
  }
}
