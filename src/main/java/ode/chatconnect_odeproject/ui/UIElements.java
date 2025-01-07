package ode.chatconnect_odeproject.ui;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

public class UIElements {

    /**
     * Erstellt die linke Seitenleiste mit Benutzerdetails.
     *
     * @param username Der Benutzername.
     * @return Ein AnchorPane mit der linken Seitenleiste.
     */
    public static AnchorPane createLeftPane(String username) {
        AnchorPane paneLeft = new AnchorPane();
        paneLeft.setPrefSize(139, 600);
        paneLeft.setId("pane_left");

        // Benutzericon
        ImageView icon_person = new ImageView("/ode/chatconnect_odeproject/personIcon.png");
        icon_person.setFitHeight(100);
        icon_person.setFitWidth(100);
        icon_person.setLayoutX(17);
        icon_person.setLayoutY(116);

        // Benutzername
        Label lbl_PersonName = new Label(username);
        lbl_PersonName.setLayoutX(45);
        lbl_PersonName.setLayoutY(226);
        lbl_PersonName.setFont(new Font("Dubai Medium", 18));
        lbl_PersonName.setStyle("-fx-text-fill: white;");

        // Einstellungen
        ImageView icon_einstellungen = new ImageView("/ode/chatconnect_odeproject/settingsIcon.png");
        icon_einstellungen.setFitHeight(20);
        icon_einstellungen.setFitWidth(20);
        icon_einstellungen.setLayoutX(25);
        icon_einstellungen.setLayoutY(280);

        Button btn_einstellungen = new Button("Einstellungen");
        btn_einstellungen.setLayoutX(40);
        btn_einstellungen.setLayoutY(280);
        btn_einstellungen.setId("btn_einstellungen");

        paneLeft.getChildren().addAll(btn_einstellungen, icon_einstellungen, lbl_PersonName, icon_person);

        return paneLeft;
    }

    /**
     * Erstellt die mittlere Pane mit einer Kontaktliste und einer Suchleiste.
     *
     * @param contactList Eine VBox, die die Kontaktliste enthält.
     * @return Ein AnchorPane, das die mittlere Pane mit Kontaktliste und Suchleiste darstellt.
     */
    public static AnchorPane createMiddlePane(VBox contactList, TextField txt_search) {
        AnchorPane paneMiddle = new AnchorPane();
        paneMiddle.setPrefSize(224, 600);
        paneMiddle.setId("pane_middle");

        contactList.setLayoutX(12);
        contactList.setLayoutY(80);
        contactList.setPrefSize(203, 520);
        contactList.setSpacing(15);

        // Suchfeld
        ImageView icon_search = new ImageView("/ode/chatconnect_odeproject/searchIcon.png");
        icon_search.setFitHeight(15);
        icon_search.setFitWidth(15);
        icon_search.setLayoutX(30);
        icon_search.setLayoutY(32);


        txt_search.setPromptText("suchen...");
        txt_search.setPrefSize(190, 29);
        txt_search.setLayoutX(15);
        txt_search.setLayoutY(21);
        txt_search.setId("txt_search");

        paneMiddle.getChildren().addAll(contactList, txt_search, icon_search);
        return paneMiddle;
    }

    /**
     * Erstellt die rechte Pane, die die Chat-Ansicht, die Nachrichtenanzeige und die Eingabefelder enthält.
     *
     * @param chatBox          Die VBox, in der Nachrichten angezeigt werden.
     * @param username         Der Benutzername, der als Absender angezeigt wird.
     * @param sendButton       Der Button, mit dem Nachrichten gesendet werden können.
     * @param messageField     Das Textfeld, in das Nachrichten eingegeben werden.
     * @param lbl_chatPersonName Das Label, das den Namen des aktuellen Chat-Partners anzeigt.
     * @return Ein AnchorPane, das die rechte Pane mit der Chat-Funktionalität darstellt.
     */
    public static AnchorPane createRightPane(VBox chatBox, String username, Button sendButton, TextField messageField, Label lbl_chatPersonName) {
        AnchorPane paneRight = new AnchorPane();
        paneRight.setPrefSize(526, 600);
        paneRight.setId("pane_right");

        // Header für den Chat
        AnchorPane chatHeader = new AnchorPane();
        chatHeader.setPrefSize(545, 66);
        chatHeader.setStyle("-fx-opacity: 0.51;");

        lbl_chatPersonName.setLayoutX(30);
        lbl_chatPersonName.setLayoutY(12);
        lbl_chatPersonName.setFont(new Font("Dubai Medium", 18));
        lbl_chatPersonName.setStyle("-fx-text-fill: black;");

        chatHeader.getChildren().add(lbl_chatPersonName);

        // Body für den Chat
        chatBox.setLayoutX(3);
        chatBox.setLayoutY(3);
        chatBox.setPrefSize(539, 476);
        chatBox.setId("chat_messages");
        chatBox.setSpacing(10); // Abstand zwischen Nachrichten

        ScrollPane chatScrollPane = new ScrollPane(chatBox);
        chatScrollPane.setFitToWidth(true);
        chatScrollPane.setPrefSize(539, 476);
        chatScrollPane.setId("chat_scrollpane");

        // Eingabefeld und Buttons
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

        AnchorPane chatBody = new AnchorPane();
        chatBody.setPrefSize(545, 554);
        chatBody.getChildren().addAll(chatScrollPane, messageField, sendButton, icon_emoji);

        paneRight.getChildren().addAll(chatHeader, chatBody);

        return paneRight;
    }

    /**
     * Fügt eine Nachricht mit einem Zeitstempel in die Chatbox ein.
     *
     * @param chatMessages Die VBox, in der die Nachricht angezeigt wird.
     * @param sender       Der Name des Absenders.
     * @param message      Die Nachricht.
     * @param timestamp    Der Zeitstempel der Nachricht.
     */
    public static void addMessageToChat(VBox chatMessages, String sender, String message, String timestamp) {
        HBox messageBox = new HBox();
        messageBox.setSpacing(10); // Abstand zwischen Nachricht und Zeitstempel

        // Nachricht
        Label messageLabel = new Label(sender + ": " + message);
        messageLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black;"); // Schriftgröße und Farbe

        // Zeitstempel
        Label timestampLabel = new Label(timestamp);
        timestampLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;"); // Kleinere Schrift, graue Farbe
        timestampLabel.setMinWidth(Region.USE_PREF_SIZE);

        // Rechte Ausrichtung für Zeitstempel
        HBox.setHgrow(timestampLabel, Priority.ALWAYS);
        timestampLabel.setAlignment(Pos.CENTER_RIGHT);

        // Elemente zur Nachricht hinzufügen
        messageBox.getChildren().addAll(messageLabel, timestampLabel);

        // Nachricht zur Chatbox hinzufügen
        chatMessages.getChildren().add(messageBox);
    }
}
