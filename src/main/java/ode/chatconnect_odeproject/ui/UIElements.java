package ode.chatconnect_odeproject.ui;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UIElements {



    private static String receiverUsername;

    // Erstelle die linke Seitenleiste
    public static AnchorPane createLeftPane(String username) {
        AnchorPane paneLeft = new AnchorPane();
        paneLeft.setPrefSize(139, 600);
        paneLeft.setId("pane_left");


        //User

        ImageView icon_person = new ImageView("/ode/chatconnect_odeproject/personIcon.png");
        icon_person.setFitHeight(100);
        icon_person.setFitWidth(100);
        icon_person.setLayoutX(17);
        icon_person.setLayoutY(116);

        Label lbl_PersonName = new Label(username);
        lbl_PersonName.setLayoutX(45);
        lbl_PersonName.setLayoutY(226);
        lbl_PersonName.setFont(new Font("Dubai Medium", 18));
        lbl_PersonName.setStyle("-fx-text-fill: white;");

        //Einstellungen
        ImageView icon_einstellungen = new ImageView("/ode/chatconnect_odeproject/settingsIcon.png");
        icon_einstellungen.setFitHeight(20);
        icon_einstellungen.setFitWidth(20);
        icon_einstellungen.setLayoutX(25);
        icon_einstellungen.setLayoutY(280);

        Button btn_einstellungen = new Button();
        btn_einstellungen.setText("Einstellungen");
        btn_einstellungen.setLayoutX(40);
        btn_einstellungen.setLayoutY(280);
        btn_einstellungen.setId("btn_einstellungen");

        paneLeft.getChildren().addAll(btn_einstellungen, icon_einstellungen, lbl_PersonName, icon_person);

        return paneLeft;
    }

    // Erstelle die mittlere Kontaktliste
    public static AnchorPane createMiddlePane(VBox contactList) {
        AnchorPane paneMiddle = new AnchorPane();
        paneMiddle.setPrefSize(224, 600);
        paneMiddle.setId("pane_middle");

        contactList.setLayoutX(12);
        contactList.setLayoutY(80);
        contactList.setPrefSize(203, 520);
        contactList.setSpacing(15);

        //Suchfeld

        ImageView icon_search = new ImageView("/ode/chatconnect_odeproject/searchIcon.png");
        icon_search.setFitHeight(15);
        icon_search.setFitWidth(15);
        icon_search.setLayoutX(30);
        icon_search.setLayoutY(32);


        TextField txt_search = new TextField();
        txt_search.setPromptText("suchen...");
        txt_search.setPrefSize(190, 29);
        txt_search.setLayoutX(15);
        txt_search.setLayoutY(21);
        txt_search.setId("txt_search");

        paneMiddle.getChildren().addAll(contactList, txt_search, icon_search);
        return paneMiddle;
    }

    // Erstelle das rechte Chat-Fenster
    public static AnchorPane createRightPane(TextArea txt_chatArea, String username, Button sendButton, TextField messageField, Label lbl_chatPersonName) {
        AnchorPane paneRight = new AnchorPane();
        paneRight.setPrefSize(526, 600);
        paneRight.setId("pane_right");

        VBox chatBox = new VBox();
        chatBox.setPrefSize(545, 600);

        AnchorPane chatHeader = new AnchorPane();
        chatHeader.setPrefSize(545, 66);
        chatHeader.setStyle("-fx-opacity: 0.51;");

        lbl_chatPersonName.setLayoutX(30);
        lbl_chatPersonName.setLayoutY(12);
        lbl_chatPersonName.setFont(new Font("Dubai Medium", 18));
        lbl_chatPersonName.setStyle("text-fill: black;");

        chatHeader.getChildren().add(lbl_chatPersonName);

        AnchorPane chatBody = new AnchorPane();
        chatBody.setPrefSize(545, 554);

        txt_chatArea.setEditable(false);
        txt_chatArea.setLayoutX(3);
        txt_chatArea.setLayoutY(3);
        txt_chatArea.setPrefSize(539, 476);
        txt_chatArea.setId("txt_area");

        messageField.setLayoutX(20);
        messageField.setLayoutY(489);
        messageField.setPrefSize(380, 35);
        messageField.setPromptText("Nachricht");
        messageField.setId("txt_message");

        sendButton.setLayoutX(415);
        sendButton.setLayoutY(493);
        sendButton.setPrefSize(60, 25);
        sendButton.setId("btn_send");

        ImageView icon_emoji = new ImageView("/ode/chatconnect_odeproject/emojiIcon.png");
        icon_emoji.setFitHeight(25);
        icon_emoji.setFitWidth(25);
        icon_emoji.setLayoutX(490);
        icon_emoji.setLayoutY(495);

        chatBody.getChildren().addAll(txt_chatArea, messageField, sendButton, icon_emoji);

        chatBox.getChildren().addAll(chatHeader, chatBody);
        paneRight.getChildren().add(chatBox);

        return paneRight;
    }

}
