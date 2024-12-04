package org.superpranx.day04;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FindingXmasTest extends TestCase {
  public FindingXmasTest(String testName) {
    super(testName);
  }
  public static Test suite() {
    return new TestSuite(FindingXmasTest.class);
  }

  public void testPartOne() {
    assertEquals(2358, FindingXmas.howManyXmasIn(FindingXmas.extractListsFromInputFile("day04/input.txt")));
  }

  public void testPartTwo() {
    assertEquals(1737, FindingXmas.howManyCrossMasIn(FindingXmas.extractListsFromInputFile("day04/input.txt")));
  }
}
