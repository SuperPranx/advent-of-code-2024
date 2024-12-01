package org.superpranx.day01;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ConsolidateResearchLocationsInfoTest extends TestCase {

  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public ConsolidateResearchLocationsInfoTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(ConsolidateResearchLocationsInfoTest.class);
  }

  public void testSumOfDifferences() {
    assertEquals(
        Long.valueOf(1151792L), ConsolidateResearchLocationsInfo.calculateDistances(
            ConsolidateResearchLocationsInfo.extractListsFromInputFile(
                "day01/input.txt")));
  }

  public void testSimilarityCalculator() {
    assertEquals(
        Long.valueOf(21790168L),
        ConsolidateResearchLocationsInfo.calculateSimilarityScore(
            ConsolidateResearchLocationsInfo.extractListsFromInputFile(
                "day01/input.txt")));
  }
}
