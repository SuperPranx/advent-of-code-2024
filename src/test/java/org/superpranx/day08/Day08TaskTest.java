package org.superpranx.day08;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day08TaskTest extends TestCase {

  public Day08TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day08TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day08Task.partOne(Day08Task.extractListsFromInputFile("day08/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day08Task.partTwo(Day08Task.extractListsFromInputFile("day08/input.txt")));
  }
}
