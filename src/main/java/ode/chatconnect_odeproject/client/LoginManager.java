/**
 * Klasse LoginManager
 *
 * Verantwortlich für das Laden, Überprüfen und Verwalten von Benutzeranmeldedaten.
 */

package ode.chatconnect_odeproject.client;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ode.chatconnect_odeproject.ui.*;
import ode.chatconnect_odeproject.server.*;

public class LoginManager {

    /**
     * Initialisiert den Login-Manager und lädt Benutzerdaten aus der angegebenen Datei.
     *
     * @param filePath Der Pfad zur Datei mit den Benutzerdaten.
     */

    private Map<String, String> userCredentials = new HashMap<>();



    public LoginManager(String filePath) {
        loadUserCredentials(filePath);
    }


    /**
     * Lädt Benutzerdaten aus der Datei und speichert sie in einer Map.
     *
     * @param filePath Der Pfad zur Datei mit den Benutzerdaten.
     */
    private void loadUserCredentials(String filePath) {
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
    }



    /**
     * Überprüft, ob die eingegebenen Anmeldedaten gültig sind.
     *
     * @param username Der Benutzername.
     * @param password Das Passwort.
     * @return true, wenn die Anmeldedaten gültig sind, sonst false.
     */
    public boolean isValidUser(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    /**
     * Handhabt die Registrierung eines neuen Benutzers.
     *
     * @param newUsername Der neue Benutzername.
     * @param newPassword Das neue Passwort.
     * @param registerStage Das Registrierungsfenster.
     * @param filePath Der Pfad zur Datei mit den Benutzerdaten.
     */

    public void handleRegistration(String newUsername, String newPassword, Stage registerStage, String filePath) {
        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            showAlert("Fehler", "Benutzername und Passwort duerfen nicht leer sein.");
            return;
        }

        if (userCredentials.containsKey(newUsername)) {
            showAlert("Fehler", "Benutzername bereits vergeben.");
            return;
        }

        try {
            Files.write(Paths.get(filePath),
                    (newUsername + ":" + newPassword + System.lineSeparator()).getBytes(),
                    StandardOpenOption.APPEND);

            userCredentials.put(newUsername, newPassword);

            showAlert("Erfolg", "Registrierung erfolgreich. Du kannst dich jetzt anmelden.");
            registerStage.close();
        } catch (IOException e) {
            showAlert("Fehler", "Benutzer konnte nicht gespeichert werden.");
        }
    }

    /**
     * Zeigt eine Fehlermeldung bei ungültigen Anmeldedaten an.
     */

    public void showLoginError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText("Ungueltige Anmeldedaten");
        alert.setContentText("Bitte ueberpruefe deinen Benutzernamen und dein Passwort.");
        alert.showAndWait();
    }

    /**
     * Zeigt eine generische Benachrichtigung an.
     *
     * @param title Der Titel der Benachrichtigung.
     * @param message Die Nachricht.
     */

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void changePassord(String username, String oldPassword, String newPassword, String filePath) {
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
        }
    }


}