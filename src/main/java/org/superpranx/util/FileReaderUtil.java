package org.superpranx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class FileReaderUtil {

  private FileReaderUtil() {
  }

  public static List<String> getListOfLines(String filePath) {
    try (InputStream inputStream = FileReaderUtil.class.getClassLoader()
        .getResourceAsStream(filePath)) {
      assert inputStream != null;
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      return reader.lines().toList();
    } catch (IOException e) {
      throw new RuntimeException("IO problem while reading file", e);
    }
  }
}
