package sample;

import data.TelefonEntry;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ui.EntryArea;

class AddRow {
    private final AnchorPane pane = new AnchorPane();
    private final TextField firstnameInput = new TextField("Vorname ");
    private final TextField lastnameInput = new TextField("Nachname ");
    private final TextField numberInput = new TextField("Telefonnummer ");
    private final Button addButton = new Button("Einfügen");
    private final Button deleteButton = new Button("Entfernen");
    private final Button saveButton = new Button("Speichern");

    AddRow() {

        AnchorPane.setLeftAnchor(firstnameInput, 10.0);
        AnchorPane.setTopAnchor(firstnameInput, 10.0);
        AnchorPane.setBottomAnchor(firstnameInput, 10.0);
        firstnameInput.setPrefWidth(75.5);

        AnchorPane.setLeftAnchor(lastnameInput, 95.5);
        AnchorPane.setTopAnchor(lastnameInput, 10.0);
        AnchorPane.setBottomAnchor(lastnameInput, 10.0);
        lastnameInput.setPrefWidth(75.5);

        AnchorPane.setLeftAnchor(numberInput, 181.0);
        AnchorPane.setTopAnchor(numberInput, 10.0);
        AnchorPane.setBottomAnchor(numberInput, 10.0);
        numberInput.setPrefWidth(75.5);

        AnchorPane.setTopAnchor(saveButton, 10.0);
        AnchorPane.setRightAnchor(saveButton, 10.0);
        AnchorPane.setBottomAnchor(saveButton, 10.0);
        saveButton.setPrefWidth(70.0);

        AnchorPane.setTopAnchor(deleteButton, 10.0);
        AnchorPane.setRightAnchor(deleteButton, 98.0);
        AnchorPane.setBottomAnchor(deleteButton, 10.0);
        deleteButton.setPrefWidth(70.0);

        AnchorPane.setTopAnchor(addButton, 10.0);
        AnchorPane.setRightAnchor(addButton, 188.0);
        AnchorPane.setBottomAnchor(addButton, 10.0);
        addButton.setPrefWidth(70.0);

        pane.getChildren().addAll(firstnameInput, lastnameInput, numberInput, addButton, deleteButton, saveButton);

    }

    private Button getSaveButton() {
        return saveButton;
    }

    private Button getDeleteButton() {
        return deleteButton;
    }

    private Button getAddButton() {
        return addButton;
    }

    private String getFirstnameInput() {
        return firstnameInput.getText();
    }

    private String getLastnameInput() {
        return lastnameInput.getText();
    }

    private String getNumberInput() {
        return numberInput.getText();
    }

    Node getPane() {
        return pane;
    }

    void addButton(AddRow addRow, ObservableList<TelefonEntry> list) {
        addRow.getAddButton().setOnMouseClicked(event -> list.add(new TelefonEntry(addRow.getLastnameInput(), addRow.getFirstnameInput(), addRow.getNumberInput())));
    }

    void deleteButton(AddRow addRow, ObservableList<TelefonEntry> list, EntryArea entryArea) {
        addRow.getDeleteButton().setOnMouseClicked(event -> list.removeAll(entryArea.getSelectedEntries()));
    }

    void saveButton(AddRow addRow, ObservableList<TelefonEntry> list) {
        addRow.getSaveButton().setOnMouseClicked(event -> FileSystem.writeFile(list));
    }
}
