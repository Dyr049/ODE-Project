package ode.chatconnect_odeproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {

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
    private Label lbl_titel;

    @FXML
    private Hyperlink link_anmelden;

    @FXML
    private Hyperlink link_registration;


    // Funktion um zum Login Anzeige zu wechseln
    public void swapToLogin(ActionEvent actionEvent) {
        if (!anchorPane_Login.isVisible()){
            anchorPane_Login.setVisible(true);
            anchorPane_Registration.setVisible(false);
        } else {
            anchorPane_Login.setVisible(true);
            anchorPane_Registration.setVisible(false);
        }
    }

    // Funktion um zur Registrierungs-Anzeige zu wechseln
    public void swapToRegistration(ActionEvent actionEvent) {
        if (!anchorPane_Registration.isVisible()){
            anchorPane_Login.setVisible(false);
            anchorPane_Registration.setVisible(true);
        } else {
            anchorPane_Login.setVisible(false);
            anchorPane_Registration.setVisible(true);
        }
    }
}
