package sample;

import data.TelefonEntry;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ui.EntryArea;

import java.util.List;
import java.util.function.Predicate;


class SearchArea {

    private final AnchorPane pane = new AnchorPane();
    private final TextField searchFieldMain = new TextField();
    private final TextField searchFieldImport = new TextField();
    private final Button loadButton = new Button("Laden");
    private final Button importButton = new Button("Import");

    SearchArea() {

        AnchorPane.setLeftAnchor(searchFieldMain, 90.0);
        AnchorPane.setTopAnchor(searchFieldMain, 10.0);
        AnchorPane.setBottomAnchor(searchFieldMain, 10.0);
        searchFieldMain.setPrefWidth(167.0);

        AnchorPane.setTopAnchor(searchFieldImport, 10.0);
        AnchorPane.setBottomAnchor(searchFieldImport, 10.0);
        AnchorPane.setRightAnchor(searchFieldImport, 90.0);
        searchFieldImport.setPrefWidth(167.0);

        AnchorPane.setLeftAnchor(loadButton, 10.0);
        AnchorPane.setTopAnchor(loadButton, 10.0);
        AnchorPane.setBottomAnchor(loadButton, 10.0);
        loadButton.setPrefWidth(70.0);

        AnchorPane.setTopAnchor(importButton, 10.0);
        AnchorPane.setRightAnchor(importButton, 10.0);
        AnchorPane.setBottomAnchor(importButton, 10.0);
        importButton.setPrefWidth(70.0);

        pane.getChildren().addAll(searchFieldMain, loadButton, importButton, searchFieldImport);
    }

    private Button getLoadButton() {
        return loadButton;
    }

    Button getImportButton() {
        return importButton;
    }

    private TextField getSearchFieldMain() {
        return searchFieldMain;
    }

    TextField getSearchFieldImport() {
        return searchFieldImport;
    }

    Node getPane() {
        return pane;
    }

    boolean filter(String newValue, TelefonEntry TelefonEntry, String lowerCaseFilter) {
        if (TelefonEntry.getFirstName().contains(newValue)) {
            return true;
        } else if ((TelefonEntry.getLastName().toLowerCase().contains(lowerCaseFilter))) {
            return true;
        } else return (TelefonEntry.getNumber().toLowerCase().contains(lowerCaseFilter));
    }

    void searchField(SearchArea searchArea, EntryArea entryArea, FilteredList<TelefonEntry> filteredData) {
        searchArea.getSearchFieldMain().setOnKeyReleased(event -> {
            searchArea.getSearchFieldMain().textProperty().addListener(((observable, oldValue, newValue) -> filteredData.setPredicate((Predicate<? super TelefonEntry>) TelefonEntry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return searchArea.filter(newValue, TelefonEntry, lowerCaseFilter);
            })));
            SortedList<TelefonEntry> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(entryArea.getTableView().comparatorProperty());
            entryArea.setItems(sortedData);
        });
    }

    void loadButton(SearchArea searchArea, ObservableList<TelefonEntry> list) {
        searchArea.getLoadButton().setOnMouseClicked(event -> {
            List<TelefonEntry> fromFile = FileSystem.readEntriesFromFile();
            if (fromFile != null) {
                System.out.println("Data Load ✔");
                list.addAll(fromFile);
            }
        });
    }


}

