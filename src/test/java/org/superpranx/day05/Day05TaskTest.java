package org.superpranx.day05;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day05TaskTest extends TestCase {

  public Day05TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day05TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day05Task.partOne(Day05Task.extractListsFromInputFile("day05/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day05Task.partTwo(Day05Task.extractListsFromInputFile("day05/input.txt")));
  }
}
