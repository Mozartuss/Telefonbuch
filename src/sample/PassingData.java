package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PassingData {
    private final AnchorPane pane = new AnchorPane();
    private final Button mainToGuestButton = new Button("-->");
    private final Button guestToMainButton = new Button("<--");

    public PassingData() {

        AnchorPane.setLeftAnchor(mainToGuestButton, 45.0);
        AnchorPane.setTopAnchor(mainToGuestButton, 300.0);
        mainToGuestButton.setPrefWidth(70.0);
        mainToGuestButton.setPrefHeight(10.0);


        AnchorPane.setLeftAnchor(guestToMainButton, 45.0);
        AnchorPane.setBottomAnchor(guestToMainButton, 300.0);
        guestToMainButton.setPrefWidth(70.0);
        guestToMainButton.setPrefHeight(10.0);

        pane.getChildren().addAll(guestToMainButton, mainToGuestButton);
    }

    public Button getMainToGuestButton() {
        return mainToGuestButton;
    }

    public Button getGuestToMainButton() {
        return guestToMainButton;
    }

    public Node getPane() {
        return pane;
    }
}
