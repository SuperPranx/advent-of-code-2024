package org.superpranx.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.superpranx.util.FileReaderUtil;

public class Day08Task {

  private static int mapSizeX = -1;
  private static int mapSizeY = -1;

  private static final BiFunction<Location, Location, List<Location>> antiNodesPerAntennaePair =
      (antenna1, antenna2) -> {
        List<Location> antiNodes = new ArrayList<>();
        int deltaX = antenna1.x - antenna2.x;
        int deltaY = antenna1.y - antenna2.y;

        createAndAddNodeIfValid(antiNodes, antenna2, -deltaX, -deltaY);
        createAndAddNodeIfValid(antiNodes, antenna1, deltaX, deltaY);
        return antiNodes;
      };

  private static final BiFunction<Location, Location, List<Location>> antiNodesPerResonantAntennaePair =
      (antenna1, antenna2) -> {
        List<Location> antiNodes = new ArrayList<>();
        antiNodes.add(antenna1);
        antiNodes.add(antenna2);
        int deltaX = antenna1.x - antenna2.x;
        int deltaY = antenna1.y - antenna2.y;

        Location antiNode = antenna1;
        while (antiNode.isWithinMap()) {
          antiNode = createAndAddNodeIfValid(antiNodes, antiNode, deltaX, deltaY);
        }

        antiNode = antenna2;
        while (antiNode.isWithinMap()) {
          antiNode = createAndAddNodeIfValid(antiNodes, antiNode, -deltaX, -deltaY);
        }
        
        return antiNodes;
      };

  private static Location createAndAddNodeIfValid(List<Location> antiNodes, Location antenna, int deltaX, int deltaY) {
    Location antiNode = new Location(antenna.x + deltaX, antenna.y + deltaY);
    if (antiNode.isWithinMap()) {
      antiNodes.add(antiNode);
    }
    return antiNode;
  }

  private Day08Task() {
  }

  public static int partOne(String[][] map) {
    return countAllAntiNodes(map, regularNodeExtraction);
  }

  public static int partTwo(String[][] map) {
    return countAllAntiNodes(map, regularNodeExtractionWithResonance);
  }

  public static int countAllAntiNodes(String[][] map, Function<List<Location>, HashSet<Location>> nodeExtractor) {
    mapSizeX = map.length;
    mapSizeY = map[0].length;
    Map<String, List<Location>> mapOfLocationsPerCode = mapOfAntennaLocationsPerCode(map);
    HashSet<Location> uniqueAntiNodes = mapOfLocationsPerCode.keySet()
        .stream()
        .map(mapOfLocationsPerCode::get)
        .filter(locations -> locations.size() > 1)
        .map(nodeExtractor).reduce(
            new HashSet<>(), (collector, antiNodes) -> {
              collector.addAll(antiNodes);
              return collector;
            });
    return uniqueAntiNodes.size();
  }

  private static final Function<List<Location>, HashSet<Location>> regularNodeExtraction = (antennae) -> {
    int howMany = antennae.size();
    HashSet<Location> antiNodes = new HashSet<>();
    for (int i = 0; i < howMany - 1; i++) {
      for (int j = i + 1; j < howMany; j++) {
        antiNodes.addAll(antiNodesPerAntennaePair.apply(antennae.get(i), antennae.get(j)));
      }
    }
    return antiNodes;
  };

  private static final Function<List<Location>, HashSet<Location>> regularNodeExtractionWithResonance = (antennae) -> {
    int howMany = antennae.size();
    HashSet<Location> antiNodes = new HashSet<>();
    for (int i = 0; i < howMany - 1; i++) {
      for (int j = i + 1; j < howMany; j++) {
        antiNodes.addAll(antiNodesPerResonantAntennaePair.apply(antennae.get(i), antennae.get(j)));
      }
    }
    return antiNodes;
  };

  private static Map<String, List<Location>> mapOfAntennaLocationsPerCode(String[][] map) {
    Map<String, List<Location>> mapOfLocationsPerCode = new HashMap<>();
    for (int x = 0; x < map.length; x++) {
      for (int y = 0; y < map[x].length; y++) {
        if (!".".equals(map[x][y])) {
          Location location = new Location(x, y);
          List<Location> antennaeForCode = mapOfLocationsPerCode.getOrDefault(map[x][y], new ArrayList<>());
          antennaeForCode.add(location);
          mapOfLocationsPerCode.put(map[x][y], antennaeForCode);
        }
      }
    }
    return mapOfLocationsPerCode;
  }

  public static String[][] extractListsFromInputFile(String inputFilePath) {
    List<String[]> asList = FileReaderUtil.getListOfLines(inputFilePath)
        .stream()
        .map(line -> line.split(""))
        .toList();
    String[][] map = new String[asList.size()][];
    return asList.toArray(map);
  }

  public record Location(int x, int y) {

    boolean isWithinMap() {
      return x >= 0 && x < mapSizeX && y >= 0 && y < mapSizeY;
    }
  }
}
