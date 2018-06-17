package sample;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.TelefonEntry;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class FileSystem {

  private static Path path1 = Paths.get("C:\\Users\\LENOVO\\OneDrive\\UNI\\Software-Engineering 2 X\\Praktikum\\Telefonbuch\\TelefonbuchCode\\src\\main\\resources\\TelefonEntries.json");

  static List<TelefonEntry> readEntriesFromFile() {
    List<TelefonEntry> entries = new ArrayList<>();
    try (InputStream is = Files.newInputStream(path1)) {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode rootArray = mapper.readTree(is);
      rootArray(entries, rootArray);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return entries;
  }

  static void rootArray(List<TelefonEntry> entries, JsonNode rootArray) {
    for (JsonNode root : rootArray) {
      String firstName = root.path("firstName").asText();
      String lastName = root.path("lastName").asText();
      String number = root.path("number").asText();
      entries.add(new TelefonEntry(lastName, firstName, number));
    }
  }

  static void writeFile(List<TelefonEntry> entries) {
    JsonFactory factory = new JsonFactory();
    ImportList.writeData(entries, path1, factory);

  }

}




