// --- PACKAGE CLIENT ---

/**
 * Klasse ChatClient
 *
 * Einstiegspunkt des Client-Anteils der Anwendung. Diese Klasse initialisiert die
 * Anwendung, zeigt das Login-Fenster an und verwaltet den Wechsel zwischen den Fenstern.
 */
package ode.chatconnect_odeproject.client;

import javafx.application.Application;
import javafx.stage.Stage;

import ode.chatconnect_odeproject.server.*;
import ode.chatconnect_odeproject.ui.*;

public class ChatClient extends Application {

    private String username;
    private LoginManager loginManager;

    /**
     * Startet die JavaFX-Anwendung.
     *
     * @param primaryStage Die Hauptbühne der JavaFX-Anwendung.
     */
    @Override
    public void start(Stage primaryStage) {
        // Login- und Registrierungsmanager initialisieren
        loginManager = new LoginManager("users.txt");

        // Erstes Login Fenster anzeigen
        showLoginWindow(primaryStage);
    }

    /**
     * Zeigt das Login-Fenster an.
     *
     * @param primaryStage Die Hauptbühne der JavaFX-Anwendung.
     */
    private void showLoginWindow(Stage primaryStage) {
        LoginWindow loginWindow = new LoginWindow(loginManager, username -> {
            this.username = username;
            showMainWindow(primaryStage);
        });

        loginWindow.show(primaryStage);
    }

    /**
     * Zeigt das Hauptfenster nach erfolgreicher Anmeldung an.
     *
     * @param primaryStage Die Hauptbühne der JavaFX-Anwendung.
     */
    private void showMainWindow(Stage primaryStage) {
        System.out.println("Erfolgreich eingeloggt als: " + username);
        MainWindow mainWindow = new MainWindow(username);
        mainWindow.show(primaryStage);
    }

    /**
     * Hauptmethode der Anwendung.
     *
     * @param args Argumente der Kommandozeile.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
