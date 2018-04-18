package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class SearchArea {

    private final AnchorPane pane = new AnchorPane();
    private final TextField searchField = new TextField();
    private final Button loadButton = new Button("Laden");
    private final Button importButton = new Button("Import");

    public TextField getSearchField(){
        return searchField;
    }
    public SearchArea() {

        AnchorPane.setLeftAnchor(searchField, 90.0);
        AnchorPane.setTopAnchor(searchField, 10.0);
        AnchorPane.setBottomAnchor(searchField, 10.0);
        AnchorPane.setRightAnchor(searchField, 90.0);

        AnchorPane.setLeftAnchor(loadButton, 10.0);
        AnchorPane.setTopAnchor(loadButton, 10.0);
        AnchorPane.setBottomAnchor(loadButton, 10.0);
        loadButton.setPrefWidth(70.0);

        AnchorPane.setTopAnchor(importButton, 10.0);
        AnchorPane.setRightAnchor(importButton, 10.0);
        AnchorPane.setBottomAnchor(importButton, 10.0);
        importButton.setPrefWidth(70.0);

        pane.getChildren().addAll(searchField, loadButton, importButton);
    }

    public Button getLoadButton() {
        return loadButton;
    }

    public Button getImportButton() {
        return importButton;
    }

    public Node getPane() {
        return pane;
    }


}

