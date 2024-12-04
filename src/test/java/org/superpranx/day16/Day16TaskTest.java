package org.superpranx.day16;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day16TaskTest extends TestCase {

  public Day16TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day16TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day16Task.partOne(Day16Task.extractListsFromInputFile("day16/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day16Task.partTwo(Day16Task.extractListsFromInputFile("day16/input.txt")));
  }
}
