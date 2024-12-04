package org.superpranx.day22;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day22TaskTest extends TestCase {

  public Day22TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day22TaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, Day22Task.partOne(Day22Task.extractListsFromInputFile("day22/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, Day22Task.partTwo(Day22Task.extractListsFromInputFile("day22/input.txt")));
  }
}
