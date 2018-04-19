package sample;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.TelefonEntry;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        SearchArea searchArea = new SearchArea();
        AddRow addRow = new AddRow();
        MoveData moveData = new MoveData();
        ObservableList<TelefonEntry> list = FXCollections.observableArrayList();
        ObservableList<TelefonEntry> list2 = FXCollections.observableArrayList();
        ui.EntryArea entryArea = new ui.EntryArea(list);
        ui.EntryAreaProfBook entryAreaProfBook = new ui.EntryAreaProfBook(list2);

        moveData.getGuestToMainButton().setOnMouseClicked(event -> list.add(new TelefonEntry(entryAreaProfBook.getSelectedEntries())));  //TODO move between the GUEST table to my MAIN table

        addRow.getAddButton().setOnMouseClicked(event -> list.add(new TelefonEntry(addRow.getFirstnameInput(), addRow.getLastnameInput(), addRow.getNumberInput())));
        addRow.getDeleteButton().setOnMouseClicked(event -> list.removeAll(entryArea.getSelectedEntries()));
        addRow.getSaveButton().setOnMouseClicked(event -> FileSystem.writeFile(list));

        FilteredList<TelefonEntry> filteredData = new FilteredList<>(list, event -> true);
        FilteredList<TelefonEntry> filteredData2 = new FilteredList<>(list2, event -> true);

        searchArea.getSearchFieldMain().setOnKeyReleased(event -> {
            searchArea.getSearchFieldMain().textProperty().addListener(((observable, oldValue, newValue) -> filteredData.setPredicate((Predicate<? super TelefonEntry>) TelefonEntry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return searchArea.Filter(newValue, TelefonEntry, lowerCaseFilter);
            })));
            SortedList<TelefonEntry> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(entryArea.getTableView().comparatorProperty());
            entryArea.setItems(sortedData);
        });
        searchArea.getSearchFieldImport().setOnKeyReleased(event -> {
            searchArea.getSearchFieldImport().textProperty().addListener(((observable, oldValue, newValue) -> filteredData2.setPredicate((Predicate<? super TelefonEntry>) TelefonEntry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return searchArea.Filter(newValue, TelefonEntry, lowerCaseFilter);
            })));
            SortedList<TelefonEntry> sortedData2 = new SortedList<>(filteredData2);
            sortedData2.comparatorProperty().bind(entryAreaProfBook.getTableView2().comparatorProperty());
            entryAreaProfBook.setItems(sortedData2);
        });
        searchArea.getImportButton().setOnMouseClicked(event -> {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Telefonbuch", "*.json"));
            File userDirectory = new File("C:/Users/LENOVO/Desktop/UNI/SE SS18/Praktikum/Projekte/Telefonbuch");
            fileChooser.setInitialDirectory(userDirectory);
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            Path path = Paths.get(selectedFile.toString());
            List<TelefonEntry> entries = new ArrayList<>();
            try (InputStream is = (Files.newInputStream(path))) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootArray = mapper.readTree(is);
                FileSystem.rootArray(entries, rootArray);
            } catch (IOException e) {
                e.printStackTrace();
            }
            list2.addAll(entries);
        });
        searchArea.getLoadButton().setOnMouseClicked(event -> {
            List<TelefonEntry> fromFile = FileSystem.readEntriesFromFile();
            if (fromFile != null) {
                System.out.println("Data Load");
                list.addAll(fromFile);
            }
        });

        root.setTop(searchArea.getPane());
        root.setLeft(entryArea.getAnchorPane());
        root.setCenter(moveData.getPane());
        root.setRight(entryAreaProfBook.getAnchorPane());

        root.setBottom(addRow.getPane());

        primaryStage.setTitle("Telefonbuch");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

