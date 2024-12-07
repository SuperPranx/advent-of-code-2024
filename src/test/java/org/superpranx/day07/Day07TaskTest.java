package org.superpranx.day07;

import java.math.BigInteger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.lang3.time.StopWatch;

public class Day07TaskTest extends TestCase {

  public Day07TaskTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(Day07TaskTest.class);
  }

  public void testPartOne() {
    BigInteger result = Day07Task.partOne(Day07Task.extractListsFromInputFile("day07/input.txt"));
    System.out.println("Result: " + result);
    assertEquals(0, new BigInteger("6083020304036").compareTo(result));
  }

  public void testPartTwo() {
    StopWatch stopWatch = StopWatch.createStarted();
    BigInteger result = Day07Task.partTwo(Day07Task.extractListsFromInputFile("day07/input.txt"));
    stopWatch.stop();
    System.out.println("Result: " + result);
    System.out.println("Duration: " + stopWatch.getDuration().toMillis());
    assertEquals(0, new BigInteger("59002246504791").compareTo(result));
  }
}
