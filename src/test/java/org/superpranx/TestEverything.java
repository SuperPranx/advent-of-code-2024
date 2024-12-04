package org.superpranx;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.superpranx.day01.ConsolidateResearchLocationsInfoTest;
import org.superpranx.day02.RedNosedReactorAnalysisTest;
import org.superpranx.day03.TobogganRentalMemoryExtractorTest;
import org.superpranx.day04.FindingXmasTest;
import org.superpranx.day05.Day05TaskTest;
import org.superpranx.day06.Day06TaskTest;
import org.superpranx.day07.Day07TaskTest;
import org.superpranx.day08.Day08TaskTest;
import org.superpranx.day09.Day09TaskTest;
import org.superpranx.day10.Day10TaskTest;
import org.superpranx.day11.Day11TaskTest;
import org.superpranx.day12.Day12TaskTest;
import org.superpranx.day13.Day13TaskTest;
import org.superpranx.day14.Day14TaskTest;
import org.superpranx.day15.Day15TaskTest;
import org.superpranx.day16.Day16TaskTest;
import org.superpranx.day17.Day17TaskTest;
import org.superpranx.day18.Day18TaskTest;
import org.superpranx.day19.Day19TaskTest;
import org.superpranx.day20.Day20TaskTest;
import org.superpranx.day21.Day21TaskTest;
import org.superpranx.day22.Day22TaskTest;
import org.superpranx.day23.Day23TaskTest;
import org.superpranx.day24.Day24TaskTest;
import org.superpranx.day25.Day25TaskTest;

public class TestEverything extends TestCase {

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ConsolidateResearchLocationsInfoTest.class);
    suite.addTestSuite(RedNosedReactorAnalysisTest.class);
    suite.addTestSuite(TobogganRentalMemoryExtractorTest.class);
    suite.addTestSuite(FindingXmasTest.class);
    suite.addTestSuite(Day05TaskTest.class);
    suite.addTestSuite(Day06TaskTest.class);
    suite.addTestSuite(Day07TaskTest.class);
    suite.addTestSuite(Day08TaskTest.class);
    suite.addTestSuite(Day09TaskTest.class);
    suite.addTestSuite(Day10TaskTest.class);
    suite.addTestSuite(Day11TaskTest.class);
    suite.addTestSuite(Day12TaskTest.class);
    suite.addTestSuite(Day13TaskTest.class);
    suite.addTestSuite(Day14TaskTest.class);
    suite.addTestSuite(Day15TaskTest.class);
    suite.addTestSuite(Day16TaskTest.class);
    suite.addTestSuite(Day17TaskTest.class);
    suite.addTestSuite(Day18TaskTest.class);
    suite.addTestSuite(Day19TaskTest.class);
    suite.addTestSuite(Day20TaskTest.class);
    suite.addTestSuite(Day21TaskTest.class);
    suite.addTestSuite(Day22TaskTest.class);
    suite.addTestSuite(Day23TaskTest.class);
    suite.addTestSuite(Day24TaskTest.class);
    suite.addTestSuite(Day25TaskTest.class);
    return suite;
  }
}
