package org.superpranx.day13;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day13TaskTest extends TestCase {

  public Day13TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day13TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day13Task.partOne(Day13Task.extractListsFromInputFile("day13/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day13Task.partTwo(Day13Task.extractListsFromInputFile("day13/input.txt")));
  }
}
