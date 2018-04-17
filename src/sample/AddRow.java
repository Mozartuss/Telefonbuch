package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddRow {
    private final AnchorPane pane = new AnchorPane();
    private final TextField firstnameInput = new TextField("Vorname ");
    private final TextField lastnameInput = new TextField("Nachname ");
    private final TextField numberInput = new TextField("Telefonnummer ");
    private final Button addButton = new Button("Einfügen");
    private final Button deleteButton = new Button("Entfernen");
    private final Button saveButton = new Button("Speichern");

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getAddButton() {
        return addButton;
    }

    public String getFirstnameInput() {
        return firstnameInput.getText();
    }

    public String getLastnameInput() {
        return lastnameInput.getText();
    }

    public String getNumberInput() {
        return numberInput.getText();
    }

    public AddRow() {

        AnchorPane.setLeftAnchor(firstnameInput, 10.0);
        AnchorPane.setTopAnchor(firstnameInput, 10.0);
        AnchorPane.setBottomAnchor(firstnameInput, 10.0);
        AnchorPane.setRightAnchor(firstnameInput, 485.0);

        AnchorPane.setLeftAnchor(lastnameInput, 125.0);
        AnchorPane.setTopAnchor(lastnameInput, 10.0);
        AnchorPane.setBottomAnchor(lastnameInput, 10.0);
        AnchorPane.setRightAnchor(lastnameInput, 360.0);

        AnchorPane.setLeftAnchor(numberInput, 250.0);
        AnchorPane.setTopAnchor(numberInput, 10.0);
        AnchorPane.setBottomAnchor(numberInput, 10.0);
        AnchorPane.setRightAnchor(numberInput, 235.0);

        AnchorPane.setTopAnchor(addButton, 10.0);
        AnchorPane.setRightAnchor(addButton, 160.0);
        AnchorPane.setBottomAnchor(addButton, 10.0);
        addButton.setPrefWidth(70.0);

        AnchorPane.setTopAnchor(deleteButton, 10.0);
        AnchorPane.setRightAnchor(deleteButton, 85.0);
        AnchorPane.setBottomAnchor(deleteButton, 10.0);
        deleteButton.setPrefWidth(70.0);

        AnchorPane.setTopAnchor(saveButton, 10.0);
        AnchorPane.setRightAnchor(saveButton, 10.0);
        AnchorPane.setBottomAnchor(saveButton, 10.0);
        saveButton.setPrefWidth(70.0);

        pane.getChildren().addAll(firstnameInput, lastnameInput, numberInput, addButton, deleteButton, saveButton);

    }

    public Node getPane() {
        return pane;
    }
}
