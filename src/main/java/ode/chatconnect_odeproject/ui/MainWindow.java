package ode.chatconnect_odeproject.ui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainWindow {

    private final String username;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private VBox chatBox;
    private Label lbl_chatPersonName;

    private String currentReceiver;
    private VBox contactList;
    private final Map<String, StringBuilder> chatHistories = new HashMap<>();

    /**
     * Konstruktor, der den Benutzernamen speichert.
     *
     * @param username Der Benutzername des angemeldeten Benutzers.
     */
    public MainWindow(String username) {
        this.username = username;
    }

    /**
     * Zeigt das Hauptfenster der Anwendung an und initialisiert die Benutzeroberfläche.
     *
     * @param primaryStage Die Hauptbühne der JavaFX-Anwendung.
     */
    public void show(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(900, 600);

        HBox mainLayout = new HBox();
        mainLayout.setPrefSize(900, 600);
        Button btn_einstellungen = new Button("Einstellungen");


        contactList = new VBox();
        TextField txt_search = new TextField();
        txt_search.textProperty().addListener((observable, oldValue, newValue) -> filterContactList(newValue));

        VBox contactPane = new VBox();
        contactPane.getChildren().addAll(txt_search, contactList);

        AnchorPane paneLeft = UIElements.createLeftPane(username, btn_einstellungen);
        AnchorPane paneMiddle = UIElements.createMiddlePane(contactList, txt_search);

        chatBox = new VBox();
        chatBox.setSpacing(10); // Abstand zwischen Nachrichten
        lbl_chatPersonName = new Label();
        TextField txt_message = new TextField();
        Button btn_senden = new Button("senden");
        AnchorPane paneRight = UIElements.createRightPane(chatBox, username, btn_senden, txt_message, lbl_chatPersonName);

        mainLayout.getChildren().addAll(paneLeft, paneMiddle, paneRight);
        root.getChildren().add(mainLayout);

        Scene mainScene = new Scene(root);
        mainScene.getStylesheets().add(getClass().getResource("/ode/chatconnect_odeproject/style.css").toExternalForm());

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Chat App");
        primaryStage.show();

        connectToServer(txt_message, btn_senden, btn_einstellungen);


    }

    /**
     * Verbindet den Client mit dem Server und startet den Empfang von Nachrichten.
     *
     * @param txt_message Das Textfeld für die Eingabe von Nachrichten.
     * @param btn_senden  Der Button zum Senden von Nachrichten.
     */
    private void connectToServer(TextField txt_message, Button btn_senden, Button btn_einstellungen) {
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
                    Platform.runLater(() -> UIElements.addMessageToChat(chatBox, "System", "Verbindung zum Server verloren.", ""));
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn_senden.setOnAction(e -> sendMessage(txt_message.getText()));
        Stage einstellung = new Stage();
        btn_einstellungen.setOnAction(e -> einstellungen(einstellung, username));
    }

    public void einstellungen(Stage einstellung, String username) {
        VBox einstellungen = new VBox();
        einstellungen.setStyle("-fx-padding: 10;");

        TextField oldPassword = new TextField();
        oldPassword.setPromptText("Enter your old Password here: ");

        TextField newPassword = new TextField();
        newPassword.setPromptText("Enter your new Password here: ");

        Button passwortNeu = new Button("Send");
        passwortNeu.setOnAction(e -> changePassword(username, oldPassword.getText(), newPassword.getText(), "users.txt"));

        einstellungen.getChildren().addAll(oldPassword, newPassword, passwortNeu);

        Scene scene = new Scene(einstellungen, 400, 300); // Szene erstellen
        einstellung.setTitle("Passwort aendern"); // Fenstertitel
        einstellung.setScene(scene);
        einstellung.show();

    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void changePassword(String username, String oldPassword, String newPassword, String filePath) {
        /*
        Map<String, String> userCredentials = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    userCredentials.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Benutzerdatei: " + e.getMessage());
        }

        if(userCredentials.containsKey(username) && userCredentials.get(username).equals(oldPassword)) {
            userCredentials.remove(username);
            userCredentials.remove(oldPassword);
            try {
                Files.write(Paths.get(filePath),
                        (username + ":" + newPassword + System.lineSeparator()).getBytes(),
                        StandardOpenOption.APPEND);

                userCredentials.put(username, newPassword);

                showAlert("Erfolg", "Passwort wurde erfolgreich geändert.");

            } catch (IOException e) {
                showAlert("Error", "Passwort konnte nicht erfolgreich geändert werden.");
            }
        */


        try {
            File userfile = new File(filePath);
            FileReader fr = new FileReader(userfile);
            BufferedReader Reader = new BufferedReader(fr);
            String ln = "\n";
            String Wanted = username + ":" + oldPassword;
            StringBuilder Output = new StringBuilder();
            String line;
            Boolean aenderung = false;
            while ((line = Reader.readLine()) != null) {
                    if (line.equals(Wanted)) {
                        Output.append(line.replace(oldPassword, newPassword) + ln);
                        aenderung = true;
                    } else {
                        Output.append(line + ln);
                    }
            }
            if (aenderung == false) {
                showAlert("Error", "Falsches Passwort eingegeben");
                return;
            }
            FileOutputStream fileOut = new FileOutputStream(userfile);
            fileOut.write(Output.toString().getBytes());
            fileOut.close();
            showAlert("Erfolg", "Passwort wurde erfolgreich geändert.");
        } catch (IOException ex) {

            showAlert("Error", "Passwort konnte nicht erfolgreich geändert werden.");
        }


    }


    /**
     * Initialisiert die Chat-Verläufe und fügt die Benutzer zur Kontaktliste hinzu.
     */
    private void initializeChatHistories() {
        String[] users = {"Diyar", "Omar", "Mateusz"};

        for (String user : users) {
            if (!user.equals(username)) {
                chatHistories.put(user, new StringBuilder());

                Button userButton = new Button(" " + user);
                userButton.setPrefSize(190, 40);
                userButton.setId("btn_person");
                userButton.setOnAction(e -> setChatPartner(user));

                contactList.getChildren().add(userButton);
            }
        }
    }

    /**
     * Filtert die Kontaktliste basierend auf dem Suchtext.
     *
     * @param searchText Der eingegebene Suchtext.
     */
    private void filterContactList(String searchText) {
        contactList.getChildren().clear();
        String lowerCaseSearchText = searchText.toLowerCase();

        for (Map.Entry<String, StringBuilder> entry : chatHistories.entrySet()) {
            String user = entry.getKey();
            if (user.toLowerCase().contains(lowerCaseSearchText)) {
                Button userButton = new Button(" " + user);
                userButton.setPrefSize(190, 40);
                userButton.setId("btn_person");
                contactList.getChildren().add(userButton);
                userButton.setOnAction(e -> setChatPartner(user));
            }
        }
    }

    /**
     * Setzt den aktuellen Chat-Partner und zeigt den Verlauf an.
     *
     * @param partner Der Benutzername des aktuellen Chat-Partners.
     */
    private void setChatPartner(String partner) {
        currentReceiver = partner;
        chatBox.getChildren().clear(); // Lösche aktuelle Nachrichten

        if (chatHistories.containsKey(partner)) {
            String[] messages = chatHistories.get(partner).toString().split("\n");
            for (String message : messages) {
                UIElements.addMessageToChat(chatBox, partner, message, "");
            }
        }
        lbl_chatPersonName.setText(partner);
    }

    /**
     * Verarbeitet eingehende Nachrichten vom Server.
     *
     * @param message Die eingehende Nachricht.
     */
    private void handleIncomingMessage(String message) {
        if (message.contains(":")) {
            String[] parts = message.split(":", 2);
            String sender = parts[0].trim();
            String msgWithTimestamp = parts[1].trim();

            Platform.runLater(() -> {
                if (chatHistories.containsKey(sender)) {
                    chatHistories.get(sender).append(sender).append(": ").append(msgWithTimestamp).append("\n");

                    if (sender.equals(currentReceiver)) {
                        UIElements.addMessageToChat(chatBox, sender, msgWithTimestamp, "");
                    }
                }
            });
        }
    }

    /**
     * Sendet eine Nachricht an den aktuellen Chat-Partner.
     *
     * @param message Die Nachricht, die gesendet werden soll.
     */
    private void sendMessage(String message) {
        if (currentReceiver == null || message.trim().isEmpty()) {
            return;
        }
        try {
            String formattedMessage = currentReceiver + ": " + message;
            out.writeObject(formattedMessage);
            out.flush();

            String timestamp = new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date());
            Platform.runLater(() -> {
                UIElements.addMessageToChat(chatBox, "Du", message, timestamp);
            });
        } catch (IOException e) {
            Platform.runLater(() -> {
                UIElements.addMessageToChat(chatBox, "System", "Nachricht konnte nicht gesendet werden.", "");
            });
        }
    }
}
