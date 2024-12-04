package org.superpranx.day21;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day21TaskTest extends TestCase {

  public Day21TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day21TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day21Task.partOne(Day21Task.extractListsFromInputFile("day21/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day21Task.partTwo(Day21Task.extractListsFromInputFile("day21/input.txt")));
  }
}
