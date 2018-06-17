package sample;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.TelefonEntry;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.EntryAreaProfBook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class ImportList {
  private static void writeImportFile(Stage primaryStage, List<TelefonEntry> entries) {
    final FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON-Datei", "*.json"));
    File userDirectory = new File("C:/Users/LENOVO/Desktop");
    fileChooser.setInitialDirectory(userDirectory);
    File selectedFile = fileChooser.showOpenDialog(primaryStage);
    Path path2 = Paths.get(selectedFile.toString());
    JsonFactory factory = new JsonFactory();
    writeData(entries, path2, factory);

  }

  static void writeData(List<TelefonEntry> entries, Path path2, JsonFactory factory) {
    try (OutputStream outputStream = Files.newOutputStream(path2);
         JsonGenerator jsonGenerator = factory.createGenerator(outputStream)) {
      jsonGenerator.writeStartArray();
      for (TelefonEntry entry : entries) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("lastName", entry.getLastName());
        jsonGenerator.writeStringField("firstName", entry.getFirstName());
        jsonGenerator.writeStringField("number", entry.getNumber());
        jsonGenerator.writeEndObject();
      }
      jsonGenerator.writeEndArray();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void importButton(Stage primaryStage, SearchArea searchArea, ObservableList<TelefonEntry> list2) {
    searchArea.getImportButton().setOnMouseClicked(event -> {
      final FileChooser fileChooser = new FileChooser();
      fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON-Datei", "*.json"));
      File userDirectory = new File("C:/Users/LENOVO/Desktop/UNI/SE SS18/Praktikum/Projekte/Telefonbuch");
      fileChooser.setInitialDirectory(userDirectory);
      File selectedFile = fileChooser.showOpenDialog(primaryStage);
      Path path = path(selectedFile);
      List<TelefonEntry> entries = new ArrayList<>();
      try (InputStream is = (Files.newInputStream(path))) {
        System.out.println("Pfad: " + path);
        System.out.println("Data Import ✔");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(is);
        FileSystem.rootArray(entries, rootArray);
      } catch (IOException e) {
        e.printStackTrace();
      }
      list2.addAll(entries);
    });
  }

  private Path path(File selectedFile) {
    return Paths.get(selectedFile.toString());
  }

  void saveImportList(Stage primaryStage, AddRow addRow, ObservableList<TelefonEntry> list2) {
    addRow.getSaveButton().setOnMouseClicked(event -> ImportList.writeImportFile(primaryStage, list2));
  }

  void searchAreaImportList(SearchArea searchArea, EntryAreaProfBook entryAreaProfBook, FilteredList<TelefonEntry> filteredData2) {
    searchArea.getSearchFieldImport().setOnKeyReleased(event -> {
      searchArea.getSearchFieldImport().textProperty().addListener(((observable, oldValue, newValue) -> filteredData2.setPredicate((Predicate<? super TelefonEntry>) TelefonEntry -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }
        String lowerCaseFilter = newValue.toLowerCase();
        return searchArea.filter(newValue, TelefonEntry, lowerCaseFilter);
      })));
      SortedList<TelefonEntry> sortedData2 = new SortedList<>(filteredData2);
      sortedData2.comparatorProperty().bind(entryAreaProfBook.getTableView2().comparatorProperty());
      entryAreaProfBook.setItems(sortedData2);
    });
  }
}
