package ode.chatconnect_odeproject.ui;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import ode.chatconnect_odeproject.client.*;
import ode.chatconnect_odeproject.server.*;

public class UIElements {

    // Erstelle die linke Seitenleiste
    public static AnchorPane createLeftPane(String username) {
        AnchorPane paneLeft = new AnchorPane();
        paneLeft.setPrefSize(139, 600);

        Label lbl_PersonName = new Label(username);
        lbl_PersonName.setLayoutX(27);
        lbl_PersonName.setLayoutY(226);
        lbl_PersonName.setFont(new Font("Dubai Medium", 18));
        lbl_PersonName.setStyle("-fx-text-fill: white;");

        paneLeft.getChildren().add(lbl_PersonName);
        return paneLeft;
    }

    // Erstelle die mittlere Kontaktliste
    public static AnchorPane createMiddlePane(VBox contactList) {
        AnchorPane paneMiddle = new AnchorPane();
        paneMiddle.setPrefSize(224, 600);

        contactList.setLayoutX(9);
        contactList.setLayoutY(65);
        contactList.setPrefSize(203, 535);
        contactList.setSpacing(10);
        contactList.setStyle("-fx-background-color: #FFFFFF;");

        paneMiddle.getChildren().add(contactList);
        return paneMiddle;
    }

    // Erstelle das rechte Chat-Fenster
    public static AnchorPane createRightPane(TextArea txt_chatArea, String username, Button sendButton, TextField messageField) {
        AnchorPane paneRight = new AnchorPane();
        paneRight.setPrefSize(526, 600);

        VBox chatBox = new VBox();
        chatBox.setPrefSize(545, 600);

        AnchorPane chatHeader = new AnchorPane();
        chatHeader.setPrefSize(545, 66);
        chatHeader.setStyle("-fx-opacity: 0.51;");

        Label lbl_chatPersonName = new Label("Person Name");
        lbl_chatPersonName.setLayoutX(30);
        lbl_chatPersonName.setLayoutY(12);
        lbl_chatPersonName.setFont(new Font("Dubai Medium", 18));
        lbl_chatPersonName.setStyle("-fx-text-fill: white;");

        chatHeader.getChildren().add(lbl_chatPersonName);

        AnchorPane chatBody = new AnchorPane();
        chatBody.setPrefSize(545, 554);

        txt_chatArea.setEditable(false);
        txt_chatArea.setLayoutX(3);
        txt_chatArea.setLayoutY(3);
        txt_chatArea.setPrefSize(539, 476);

        messageField.setLayoutX(20);
        messageField.setLayoutY(489);
        messageField.setPrefSize(401, 41);
        messageField.setPromptText("Nachricht");

        sendButton.setLayoutX(432);
        sendButton.setLayoutY(493);
        sendButton.setPrefSize(67, 32);

        chatBody.getChildren().addAll(txt_chatArea, messageField, sendButton);

        chatBox.getChildren().addAll(chatHeader, chatBody);
        paneRight.getChildren().add(chatBox);

        return paneRight;
    }
}
