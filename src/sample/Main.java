package sample;

import data.TelefonEntry;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        SearchArea searchArea = new SearchArea();
        AddRow addRow = new AddRow();
        MoveData moveData = new MoveData();
        ImportList importList = new ImportList();
        ObservableList<TelefonEntry> list = FXCollections.observableArrayList();
        ObservableList<TelefonEntry> list2 = FXCollections.observableArrayList();
        FilteredList<TelefonEntry> filteredData = new FilteredList<>(list, event -> true);
        FilteredList<TelefonEntry> filteredData2 = new FilteredList<>(list2, event -> true);
        ui.EntryArea entryArea = new ui.EntryArea(list);
        ui.EntryAreaProfBook entryAreaProfBook = new ui.EntryAreaProfBook(list2);

        //Buttons

        moveData.moveToMain(moveData, list, entryAreaProfBook);
        moveData.moveToGuest(moveData, list2, entryArea);
        addRow.addButton(addRow, list);
        addRow.deleteButton(addRow, list, entryArea);
        addRow.saveButton(addRow, list);
        searchArea.searchField(searchArea, entryArea, filteredData);
        importList.searchAreaImportList(searchArea, entryAreaProfBook, filteredData2);
        importList.importButton(primaryStage, searchArea, list2);
        searchArea.loadButton(searchArea, list);

        //Position

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

