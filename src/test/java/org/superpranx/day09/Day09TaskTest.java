package org.superpranx.day09;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day09TaskTest extends TestCase {

  public Day09TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day09TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day09Task.partOne(Day09Task.extractListsFromInputFile("day09/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day09Task.partTwo(Day09Task.extractListsFromInputFile("day09/input.txt")));
  }
}
