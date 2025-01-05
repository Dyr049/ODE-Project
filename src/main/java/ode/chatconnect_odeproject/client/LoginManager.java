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

    private Map<String, String> userCredentials = new HashMap<>();

    public LoginManager(String filePath) {
        loadUserCredentials(filePath);
    }

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

    public boolean isValidUser(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

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

    public void showLoginError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText("Ungueltige Anmeldedaten");
        alert.setContentText("Bitte ueberpruefe deinen Benutzernamen und dein Passwort.");
        alert.showAndWait();
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}