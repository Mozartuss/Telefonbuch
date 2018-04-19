package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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

    Button getMainToGuestButton() {
        return mainToGuestButton;
    }

    Button getGuestToMainButton() {
        return guestToMainButton;
    }

    Node getPane() {
        return pane;
    }
}
