package sample;

import data.TelefonEntry;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ui.EntryArea;
import ui.EntryAreaProfBook;

import java.util.stream.Collectors;

class MoveData {
  private final AnchorPane pane = new AnchorPane();
  private final Button mainToGuestButton = new Button("-->");
  private final Button guestToMainButton = new Button("<--");

  MoveData() {

    AnchorPane.setLeftAnchor(mainToGuestButton, 45.0);
    AnchorPane.setRightAnchor(mainToGuestButton, 45.0);
    AnchorPane.setTopAnchor(mainToGuestButton, 300.0);
    mainToGuestButton.setPrefWidth(70.0);
    mainToGuestButton.setPrefHeight(10.0);

    AnchorPane.setRightAnchor(guestToMainButton, 45.0);
    AnchorPane.setLeftAnchor(guestToMainButton, 45.0);
    AnchorPane.setBottomAnchor(guestToMainButton, 300.0);
    guestToMainButton.setPrefWidth(70.0);
    guestToMainButton.setPrefHeight(10.0);

    pane.getChildren().addAll(guestToMainButton, mainToGuestButton);
  }

  private Button getMainToGuestButton() {
    return mainToGuestButton;
  }

  private Button getGuestToMainButton() {
    return guestToMainButton;
  }

  Node getPane() {
    return pane;
  }

  void moveToGuest(MoveData moveData, ObservableList<TelefonEntry> list2, EntryArea entryArea) {
    moveData.getMainToGuestButton().setOnMouseClicked(event -> {
      String listString = entryArea.getSelectedEntries().stream().map(Object::toString).collect(Collectors.joining(", "));
      list2.addAll(entryArea.getSelectedEntries());
      System.out.println("Selected: " + listString);
    });
  }

  void moveToMain(MoveData moveData, ObservableList<TelefonEntry> list, EntryAreaProfBook entryAreaProfBook) {
    moveData.getGuestToMainButton().setOnMouseClicked(event -> {
      String listString = entryAreaProfBook.getSelectedEntries().stream().map(Object::toString).collect(Collectors.joining(", "));
      list.addAll(entryAreaProfBook.getSelectedEntries());
      System.out.println("Selected: " + listString);
    });
  }
}
