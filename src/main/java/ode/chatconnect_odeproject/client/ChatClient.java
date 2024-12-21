package ode.chatconnect_odeproject.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;

public class ChatClient extends Application {

    private static final DatagramSocket socket;

    static {
        try {
            socket = new DatagramSocket(); // initialisiert auf einem freien/verfügbaren PORT
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private static final InetAddress address;

    static {
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String identifier = "Diyar"; //Das ist unsere ID - also der Benutzername des Clients

    private static final int SERVER_PORT = 8000; //Das bleibt IMMER GLEICH!!!

    private static final TextArea messageArea = new TextArea();

    private static final TextField inputBox = new TextField();


    public static void main(String[] args) throws IOException {

        // Thread um Nachrichten zu erhalten wird initialisiert
        ClientThread clientThread = new ClientThread(socket, messageArea); //Neuer Thread wird erstellt
        clientThread.start();

        // Initialisation wird an den Server geschickt
        byte[] uuid = ("init; " + identifier).getBytes();
        DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
        socket.send(initialize);

        launch(); // GUI wird gestartet

    }

    @Override
    public void start(Stage primaryStage) {

        messageArea.setMaxWidth(500);
        messageArea.setEditable(false);


        inputBox.setMaxWidth(500);
        inputBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) { //wenn ENTER gedrückt wird
                String temp = identifier + ": " + inputBox.getText(); // Nachricht zum senden wird aus der InputBox gelesen
                messageArea.setText(messageArea.getText() + inputBox.getText() + "\n"); // Nachrichten in der messageArea werden aktualisiert
                byte[] msg = temp.getBytes(); // Nachricht wird zu bytes umgewandelt
                inputBox.setText(""); // InputBox wird geleert

                // packet wird erstellt und gesendet
                DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
                try {
                    socket.send(send);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // Ganzes UI wird angezeigt
        Scene scene = new Scene(new VBox(35, messageArea, inputBox), 550, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
