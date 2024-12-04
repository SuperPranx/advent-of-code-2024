package org.superpranx.day23;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day23TaskTest extends TestCase {

  public Day23TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day23TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day23Task.partOne(Day23Task.extractListsFromInputFile("day23/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day23Task.partTwo(Day23Task.extractListsFromInputFile("day23/input.txt")));
  }
}
