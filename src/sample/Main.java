package sample;


import data.TelefonEntry;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.function.Predicate;

// was ist das boot sdk
// und was ist der unterschied zum platform setting sdk


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileSystem fileSystem = new FileSystem();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        BorderPane root = new BorderPane();
        SearchArea searchArea = new SearchArea();
        ObservableList<TelefonEntry> list = FXCollections.observableArrayList();
        searchArea.getImportButton().setOnMouseClicked(event -> {
            List<TelefonEntry> fromFile = FileSystem.readEntriesFromFile();
            if (fromFile != null) {
                list.addAll(fromFile);
            }
        });


        ui.EntryArea entryArea = new ui.EntryArea(list);

        AddRow addRow = new AddRow();
        addRow.getAddButton().setOnMouseClicked(event -> list.add(new TelefonEntry(addRow.getFirstnameInput(), addRow.getLastnameInput(), addRow.getNumberInput())));
        addRow.getDeleteButton().setOnMouseClicked(event -> list.removeAll(entryArea.getSelectedEntries()));
        addRow.getSaveButton().setOnMouseClicked(event -> FileSystem.writeFile(list));

        FilteredList<TelefonEntry> filteredData = new FilteredList<>(list, event -> true);
        searchArea.getSearchField().setOnKeyReleased(event -> {
            searchArea.getSearchField().textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super TelefonEntry>) TelefonEntry -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (TelefonEntry.getFirstName().contains(newValue)) {
                        return true;
                    } else if ((TelefonEntry.getLastName().toLowerCase().contains(lowerCaseFilter))) {
                        return true;
                    } else if ((TelefonEntry.getNumber().toLowerCase().contains(lowerCaseFilter))) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<TelefonEntry> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(entryArea.getTableView().comparatorProperty());
            entryArea.setItems(sortedData);
        });


        root.setTop(searchArea.getPane());
        root.setCenter(entryArea.getAnchorPane());
        root.setBottom(addRow.getPane());


        primaryStage.setTitle("Ringbook");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}

