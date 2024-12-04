package org.superpranx.day07;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day07TaskTest extends TestCase {

  public Day07TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day07TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day07Task.partOne(Day07Task.extractListsFromInputFile("day07/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day07Task.partTwo(Day07Task.extractListsFromInputFile("day07/input.txt")));
  }
}
