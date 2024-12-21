package ode.chatconnect_odeproject;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    public TextField txt_register_name;
    private UserManager userManager = new UserManager(); // Benutzerverwaltung
    private String currentUserName;

    @FXML
    public AnchorPane anchorPane_Login;

    @FXML
    public AnchorPane anchorPane_Registration;

    @FXML
    private Button btn_anmelden;

    @FXML
    private Button btn_registration;

    @FXML
    private TextField lbl_benutzername;

    @FXML
    private TextField lbl_passwort;

    @FXML
    private TextField txt_register_username;

    @FXML
    private TextField txt_register_password;

    @FXML
    private Label lbl_titel;

    @FXML
    private Hyperlink link_anmelden;

    @FXML
    private Hyperlink link_registration;

    // Funktion um zum Login Anzeige zu wechseln
    public void swapToLogin(ActionEvent actionEvent) {
        anchorPane_Login.setVisible(true);
        anchorPane_Registration.setVisible(false);
    }

    // Funktion um zur Registrierungs-Anzeige zu wechseln
    public void swapToRegistration(ActionEvent actionEvent) {
        anchorPane_Login.setVisible(false);
        anchorPane_Registration.setVisible(true);
    }

    public void switchToChatView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chat.fxml"));
            Parent chatRoot = fxmlLoader.load();

            ChatController chatController = fxmlLoader.getController();
            chatController.setLoggedInUser(currentUserName);

            Stage stage = (Stage) anchorPane_Login.getScene().getWindow();
            stage.setScene(new Scene(chatRoot));
            stage.setTitle("Chat Connect - Chat");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(ActionEvent actionEvent) {
        String username = lbl_benutzername.getText();
        String password = lbl_passwort.getText();

        if (userManager.login(username, password)) {
            currentUserName = userManager.getName(username);
            System.out.println("Anmeldung erfolgreich!");
            switchToChatView();
        } else {
            showAlert("Fehler", "Ungültiger Benutzername oder Passwort.", Alert.AlertType.ERROR);
        }
    }

    public void register(ActionEvent actionEvent) {

        String name = txt_register_name.getText(); // Neues Feld für den vollständigen Namen
        String username = txt_register_username.getText();
        String password = txt_register_password.getText();

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert("Fehler", "Name, Benutzername und Passwort dürfen nicht leer sein.", Alert.AlertType.ERROR);
            return;
        }

        if (userManager.register(name, username, password)) {
            System.out.println("Registrierung erfolgreich!");
            showAlert("Erfolg", "Registrierung erfolgreich!", Alert.AlertType.INFORMATION);
        } else {
            System.out.println("Benutzername existiert bereits.");
            showAlert("Fehler", "Benutzername existiert bereits.", Alert.AlertType.ERROR);
        }
    }


    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}








