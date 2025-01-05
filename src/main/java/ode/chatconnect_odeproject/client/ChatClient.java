package ode.chatconnect_odeproject.client;

import javafx.application.Application;
import javafx.stage.Stage;

import ode.chatconnect_odeproject.server.*;
import ode.chatconnect_odeproject.ui.*;

public class ChatClient extends Application {
    private String username;
    private LoginManager loginManager;

    @Override
    public void start(Stage primaryStage) {
        // Login- und Registrierungsmanager initialisieren
        loginManager = new LoginManager("users.txt");

        // Erstes Login Fenster anzeigen
        showLoginWindow(primaryStage);
    }

    private void showLoginWindow(Stage primaryStage) {
        LoginWindow loginWindow = new LoginWindow(loginManager, username -> {
            this.username = username;
            showMainWindow(primaryStage);
        });

        loginWindow.show(primaryStage);
    }

    private void showMainWindow(Stage primaryStage) {
        System.out.println("Erfolgreich eingeloggt als: " + username);
        MainWindow mainWindow = new MainWindow(username);
        mainWindow.show(primaryStage);
    }

    // Diese main Methode bleibt unverändert
    public static void main(String[] args) {
        launch(args);
    }
}
