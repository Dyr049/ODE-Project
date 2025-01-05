package ode.chatconnect_odeproject.ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import ode.chatconnect_odeproject.client.*;
import ode.chatconnect_odeproject.server.*;

public class MainWindow {
    private final String username;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private TextArea txt_chatArea;
    private String currentReceiver;
    private VBox contactList;
    private final Map<String, StringBuilder> chatHistories = new HashMap<>();

    public MainWindow(String username) {
        this.username = username;
    }

    public void show(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(900, 600);

        HBox mainLayout = new HBox();
        mainLayout.setPrefSize(900, 600);

        // UI-Elemente erstellen
        contactList = new VBox();
        AnchorPane paneLeft = UIElements.createLeftPane(username);
        AnchorPane paneMiddle = UIElements.createMiddlePane(contactList);
        txt_chatArea = new TextArea();
        TextField txt_message = new TextField();
        Button btn_senden = new Button("send");
        AnchorPane paneRight = UIElements.createRightPane(txt_chatArea, username, btn_senden, txt_message);

        mainLayout.getChildren().addAll(paneLeft, paneMiddle, paneRight);
        root.getChildren().add(mainLayout);

        Scene mainScene = new Scene(root);
        mainScene.getStylesheets().add(getClass().getResource("/ode/chatconnect_odeproject/style.css").toExternalForm());

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Chat App");
        primaryStage.show();

        connectToServer(txt_message, btn_senden);
    }

    private void connectToServer(TextField txt_message, Button btn_senden) {
        try {
            Socket socket = new Socket("localhost", 12345);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(username);
            out.flush();

            initializeChatHistories();

            new Thread(() -> {
                try {
                    while (true) {
                        Object received = in.readObject();
                        if (received instanceof String) {
                            String message = (String) received;
                            handleIncomingMessage(message);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    Platform.runLater(() -> txt_chatArea.appendText("Verbindung zum Server verloren.\n"));
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Event für das Senden einer Nachricht
        btn_senden.setOnAction(e -> sendMessage(txt_message.getText()));
    }

    private void initializeChatHistories() {
        String[] users = {"Diyar", "Omar", "Mateusz"};

        for (String user : users) {
            if (!user.equals(username)) {
                chatHistories.put(user, new StringBuilder());

                Button userButton = new Button("Chat mit " + user);
                userButton.setPrefSize(190, 40);
                userButton.setStyle("-fx-font-size: 14px;");
                userButton.setOnAction(e -> setChatPartner(user));

                contactList.getChildren().add(userButton);
            }
        }
    }

    private void setChatPartner(String partner) {
        currentReceiver = partner;
        txt_chatArea.setText(chatHistories.get(partner).toString());
        txt_chatArea.appendText("Du chattest jetzt mit " + partner + "\n");
    }

    private void handleIncomingMessage(String message) {
        if (message.contains(":")) {
            String[] parts = message.split(":", 2);
            String sender = parts[0].trim();
            String msg = parts[1].trim();

            if (chatHistories.containsKey(sender)) {
                chatHistories.get(sender).append(sender).append(": ").append(msg).append("\n");

                if (sender.equals(currentReceiver)) {
                    Platform.runLater(() -> txt_chatArea.appendText(sender + ": " + msg + "\n"));
                }
            }
        }
    }

    private void sendMessage(String message) {
        if (currentReceiver == null) {
            txt_chatArea.appendText("Bitte wähle einen Chat-Partner aus.\n");
            return;
        }
        try {
            out.writeObject(currentReceiver + ": " + message);
            out.flush();

            chatHistories.get(currentReceiver).append("Du: ").append(message).append("\n");
            txt_chatArea.appendText("Du: " + message + "\n");
        } catch (IOException e) {
            txt_chatArea.appendText("Nachricht konnte nicht gesendet werden.\n");
        }
    }
}
