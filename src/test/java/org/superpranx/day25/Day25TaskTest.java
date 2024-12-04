package org.superpranx.day25;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day25TaskTest extends TestCase {

  public Day25TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day25TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day25Task.partOne(Day25Task.extractListsFromInputFile("day25/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day25Task.partTwo(Day25Task.extractListsFromInputFile("day25/input.txt")));
  }
}
