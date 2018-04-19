package ui;

import data.TelefonEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.util.List;

public class EntryAreaProfBook {
    private final AnchorPane anchorPane = new AnchorPane();
    private final TableView<TelefonEntry> tableView2;

    public EntryAreaProfBook(ObservableList<TelefonEntry> telefonEntries) {
        tableView2 = new TableView<>();
        AnchorPane.setLeftAnchor(tableView2, 10.0);
        AnchorPane.setRightAnchor(tableView2, 10.0);
        AnchorPane.setTopAnchor(tableView2, 0.0);
        AnchorPane.setBottomAnchor(tableView2, 0.0);
        anchorPane.getChildren().addAll(tableView2);

        Callback<TableColumn<TelefonEntry, String>, TableCell<TelefonEntry, String>> cellFactory = p -> new EntryArea.EditingCell();

        TableColumn<TelefonEntry, String> lastNameCol = new TableColumn<>("Nachname");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(cellFactory);
        lastNameCol.setOnEditCommit(t -> getCurrentRow(t).setLastName(t.getNewValue()));

        TableColumn<TelefonEntry, String> firstNameCol = new TableColumn<>("Vorname");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(cellFactory);
        firstNameCol.setOnEditCommit(t -> getCurrentRow(t).setFirstName(t.getNewValue()));

        TableColumn<TelefonEntry, String> emailCol = new TableColumn<>("Telefonnummer");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        emailCol.setCellFactory(cellFactory);
        emailCol.setOnEditCommit(t -> getCurrentRow(t).setNumber(t.getNewValue()));

        tableView2.getColumns().add(firstNameCol);
        tableView2.getColumns().add(lastNameCol);
        tableView2.getColumns().add(emailCol);
        tableView2.setItems(telefonEntries);
        tableView2.setEditable(true);


    }

    private static TelefonEntry getCurrentRow(TableColumn.CellEditEvent<TelefonEntry, String> t) {
        return t.getTableView().getItems().get(t.getTablePosition().getRow());
    }


    public TableView<TelefonEntry> getTableView2() {
        return tableView2;
    }

    private EntryAreaProfBook getInstance() {
        Object item = tableView2.getSelectionModel().selectedItemProperty().get();
        return getInstance();
    }


    public List<TelefonEntry> getItems(List<TelefonEntry> items) {
        if (items instanceof ObservableList) {
            tableView2.setItems((ObservableList<TelefonEntry>) items);
        }
        return items;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setItems(List<TelefonEntry> items) {
        if (items instanceof ObservableList) {
            tableView2.setItems((ObservableList<TelefonEntry>) items);
        } else {
            tableView2.setItems(FXCollections.observableList(items));
        }
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public ObservableList<TelefonEntry> getSelectedEntries() {
        return tableView2.getSelectionModel().getSelectedItems();
    }
}
