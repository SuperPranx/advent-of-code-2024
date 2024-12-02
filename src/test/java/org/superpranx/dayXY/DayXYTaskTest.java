package org.superpranx.dayXY;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DayXYTaskTest extends TestCase {
  public DayXYTaskTest(String testName) {
    super(testName);
  }
  public static Test suite() {
    return new TestSuite(DayXYTaskTest.class);
  }

  public void testPartOne() {
    assertEquals(123, DayXYTask.partOne(DayXYTask.extractListsFromInputFile("dayXY/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(456, DayXYTask.partTwo(DayXYTask.extractListsFromInputFile("dayXY/input.txt")));
  }
}
