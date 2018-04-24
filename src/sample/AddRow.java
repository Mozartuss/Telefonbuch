package sample;

import data.TelefonEntry;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ui.EntryArea;

class AddRow {
    private final TextField firstNameInput = new TextField("Vorname ");
    private final AnchorPane pane = new AnchorPane();
    private final TextField lastNameInput = new TextField("Nachname ");
    ImportList importList = new ImportList();
    private final TextField numberInput = new TextField("Telefonnummer ");
    private final Button addButton = new Button("Einfügen");
    private final Button deleteButton = new Button("Entfernen");
    private final Button saveButton = new Button("Speichern");

    AddRow() {

        AnchorPane.setLeftAnchor(firstNameInput, 10.0);
        AnchorPane.setTopAnchor(firstNameInput, 10.0);
        AnchorPane.setBottomAnchor(firstNameInput, 10.0);
        firstNameInput.setPrefWidth(75.5);

        AnchorPane.setLeftAnchor(lastNameInput, 95.5);
        AnchorPane.setTopAnchor(lastNameInput, 10.0);
        AnchorPane.setBottomAnchor(lastNameInput, 10.0);
        lastNameInput.setPrefWidth(75.5);

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

        pane.getChildren().addAll(firstNameInput, lastNameInput, numberInput, addButton, deleteButton, saveButton);

    }

    Button getSaveButton() {
        return saveButton;
    }

    private Button getDeleteButton() {
        return deleteButton;
    }

    private Button getAddButton() {
        return addButton;
    }

    private String getFirstNameInput() {
        return firstNameInput.getText();
    }

    private String getLastNameInput() {
        return lastNameInput.getText();
    }

    private String getNumberInput() {
        return numberInput.getText();
    }

    Node getPane() {
        return pane;
    }

    void addButton(AddRow addRow, ObservableList<TelefonEntry> list) {
        addRow.getAddButton().setOnMouseClicked(event -> list.add(new TelefonEntry(addRow.getLastNameInput(), addRow.getFirstNameInput(), addRow.getNumberInput())));
    }

    void deleteButton(AddRow addRow, ObservableList<TelefonEntry> list, EntryArea entryArea) {
        addRow.getDeleteButton().setOnMouseClicked(event -> list.removeAll(entryArea.getSelectedEntries()));
    }

    void saveButton(AddRow addRow, ObservableList<TelefonEntry> list) {
        addRow.getSaveButton().setOnMouseClicked(event -> FileSystem.writeFile(list));
    }
}
