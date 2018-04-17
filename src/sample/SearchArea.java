package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class SearchArea {

    private final AnchorPane pane = new AnchorPane();
    private final TextField searchField = new TextField();
    private final Button searchButton = new Button("Suchen");
    private final Button importButton = new Button("Import");

    public TextField getSearchField(){
        return searchField;
    }
    public Button getSearchButton(){
        return searchButton;
    }
    public Button getImportButton() {
        return importButton;
    }


    public SearchArea() {

        AnchorPane.setLeftAnchor(searchField, 90.0);
        AnchorPane.setTopAnchor(searchField, 10.0);
        AnchorPane.setBottomAnchor(searchField, 10.0);
        AnchorPane.setRightAnchor(searchField, 90.0);

        AnchorPane.setTopAnchor(searchButton, 10.0);
        AnchorPane.setRightAnchor(searchButton, 10.0);
        AnchorPane.setBottomAnchor(searchButton, 10.0);
        searchButton.setPrefWidth(70.0);

        AnchorPane.setLeftAnchor(importButton, 10.0);
        AnchorPane.setTopAnchor(importButton, 10.0);
        AnchorPane.setRightAnchor(importButton, 520.0);
        AnchorPane.setBottomAnchor(importButton, 10.0);
        importButton.setPrefWidth(70.0);

        pane.getChildren().addAll(searchField, searchButton, importButton);
    }

    public Node getPane() {
        return pane;
    }


}

