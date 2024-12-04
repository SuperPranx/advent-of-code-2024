package org.superpranx.day14;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day14TaskTest extends TestCase {

  public Day14TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day14TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day14Task.partOne(Day14Task.extractListsFromInputFile("day14/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day14Task.partTwo(Day14Task.extractListsFromInputFile("day14/input.txt")));
  }
}
