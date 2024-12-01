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
    ConsolidateResearchLocationsInfo.DistancesInput input =
        ConsolidateResearchLocationsInfo.extractListsFromInputFile("day01/input.txt");
    Long result =
        ConsolidateResearchLocationsInfo.calculateDistances(input.firstGroupLocations(), input.secondGroupLocations());
    assertEquals(Long.valueOf(1151792L), result);
  }

  public void testSimilarityCalculator() {
    ConsolidateResearchLocationsInfo.DistancesInput input =
        ConsolidateResearchLocationsInfo.extractListsFromInputFile("day01/input.txt");
    Long result =
        ConsolidateResearchLocationsInfo.calculateSimilarityScore(
            input.firstGroupLocations(),
            input.secondGroupLocations());
    assertEquals(Long.valueOf(21790168L), result);
  }
}
