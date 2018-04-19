package sample;

import data.TelefonEntry;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


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

    Button getLoadButton() {
        return loadButton;
    }

    Button getImportButton() {
        return importButton;
    }

    TextField getSearchFieldMain() {
        return searchFieldMain;
    }

    TextField getSearchFieldImport() {
        return searchFieldImport;
    }

    Node getPane() {
        return pane;
    }

    boolean Filter(String newValue, TelefonEntry TelefonEntry, String lowerCaseFilter) {
        if (TelefonEntry.getFirstName().contains(newValue)) {
            return true;
        } else if ((TelefonEntry.getLastName().toLowerCase().contains(lowerCaseFilter))) {
            return true;
        } else return (TelefonEntry.getNumber().toLowerCase().contains(lowerCaseFilter));
    }


}

