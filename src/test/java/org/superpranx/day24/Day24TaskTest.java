package org.superpranx.day24;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day24TaskTest extends TestCase {

  public Day24TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day24TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day24Task.partOne(Day24Task.extractListsFromInputFile("day24/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day24Task.partTwo(Day24Task.extractListsFromInputFile("day24/input.txt")));
  }
}
