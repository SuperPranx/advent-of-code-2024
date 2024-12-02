package org.superpranx.day03;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day03TaskTest extends TestCase {
  public Day03TaskTest(String testName) {
    super(testName);
  }
  public static Test suite() {
    return new TestSuite(Day03TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day03Task.partOne(Day03Task.extractListsFromInputFile("day03/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day03Task.partTwo(Day03Task.extractListsFromInputFile("day03/input.txt")));
  }
}
