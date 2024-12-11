package org.superpranx.day11;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Day11TaskTest extends TestCase {

  public Day11TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day11TaskTest.class);
  }

  public void testPartOne25() {
    assertEquals(123, Day11Task.partOne(Day11Task.extractListsFromInputFile("day11/input.txt"), 25));
  }

  public void testPartOne75() {
    assertEquals(456, Day11Task.partOne(Day11Task.extractListsFromInputFile("day11/input.txt"), 50));
  }
}
