package org.superpranx.day03;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TobogganRentalMemoryExtractorTest extends TestCase {

  public TobogganRentalMemoryExtractorTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(TobogganRentalMemoryExtractorTest.class);
  }

  public void testSumValidMuls() {
    assertEquals(
        184576302,
        TobogganRentalMemoryExtractor.sumValidMuls(TobogganRentalMemoryExtractor.extractInputFrom(
            "day03/input.txt")));
  }

  public void testSumExcludingDeactivatedSections() {
    assertEquals(
        118173507,
        TobogganRentalMemoryExtractor.sumExcludingDeactivatedSections(TobogganRentalMemoryExtractor.extractInputFrom(
            "day03/input.txt")));
  }
}
