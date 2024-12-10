package ode.chatconnect_odeproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class LoginController {
    private UserManager userManager = new UserManager(); // Benutzerverwaltung
    @FXML
    public AnchorPane anchorPane_Login;

    @FXML
    public AnchorPane anchorPane_Registration;

    @FXML
    private Button btn_anmelden;

    @FXML
    private Button btn_registration;

    @FXML
    private TextField lbl_mail;

    @FXML
    private TextField lbl_passwort;

    @FXML
    private TextField txt_register_mail;

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




        // Anmeldung
        public void login (ActionEvent actionEvent){
            String email = lbl_mail.getText();
            String password = lbl_passwort.getText();

            if (userManager.login(email, password)) {
                showAlert("Erfolg", "Anmeldung erfolgreich!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Fehler", "Ungültige E-Mail oder Passwort.", Alert.AlertType.ERROR);
            }
        }

        // Registrierung
        public void register (ActionEvent actionEvent){
            String email = txt_register_mail.getText();
            String password = txt_register_password.getText();

            if (userManager.register(email, password)) {
                showAlert("Erfolg", "Registrierung erfolgreich!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Fehler", "E-Mail existiert bereits.", Alert.AlertType.ERROR);
            }
        }

        private void showAlert (String title, String message, Alert.AlertType alertType){
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

