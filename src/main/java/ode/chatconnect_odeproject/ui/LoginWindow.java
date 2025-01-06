// --- PACKAGE UI ---

/**
 * Klasse LoginWindow
 *
 * Stellt die Benutzeroberfläche für die Anmeldung und Registrierung bereit.
 */
package ode.chatconnect_odeproject.ui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ode.chatconnect_odeproject.client.LoginManager;

import java.util.function.Consumer;

public class LoginWindow {



    private final LoginManager loginManager;
    private final Consumer<String> onLoginSuccess;


    /**
     * Konstruktor, der den Login-Manager und eine Callback-Funktion für erfolgreiche Anmeldungen initialisiert.
     *
     * @param loginManager   Der Login-Manager, der für die Validierung der Benutzeranmeldedaten zuständig ist.
     * @param onLoginSuccess Callback-Funktion, die bei erfolgreicher Anmeldung aufgerufen wird.
     */

    public LoginWindow(LoginManager loginManager, Consumer<String> onLoginSuccess) {
        this.loginManager = loginManager;
        this.onLoginSuccess = onLoginSuccess;
    }

    /**
     * Zeigt das Login-Fenster an und initialisiert die Benutzeroberfläche.
     *
     * @param primaryStage Die Hauptbühne der JavaFX-Anwendung.
     */

    public void show(Stage primaryStage) {
        // Layout für das Login-Fenster erstellen
        AnchorPane loginPane = new AnchorPane();
        loginPane.setPrefSize(900, 600);

        // Titel und Untertitel

        Label lbl_titel = new Label("Hallo!");
        lbl_titel.setLayoutX(380);
        lbl_titel.setLayoutY(53);
        lbl_titel.setId("lbl_titel");

        Label lbl_titel2 = new Label("Bitte geben Sie Ihre Anmeldedaten ein");
        lbl_titel2.setLayoutX(350);
        lbl_titel2.setLayoutY(131);
        lbl_titel2.setId("lbl_titel2");

        // Username Textfeld

        ImageView icon_mail = new ImageView("/ode/chatconnect_odeproject/mailIcon.png");
        icon_mail.setLayoutX(305);
        icon_mail.setLayoutY(240);
        icon_mail.setFitHeight(20);
        icon_mail.setFitWidth(20);

        TextField txtUsername = new TextField();
        txtUsername.setLayoutX(335);
        txtUsername.setLayoutY(233);
        txtUsername.setPrefWidth(250);
        txtUsername.setPrefHeight(35);
        txtUsername.setPromptText("Username");


        //Passwortfeld

        ImageView icon_password = new ImageView("/ode/chatconnect_odeproject/passwordIcon.jpg");
        icon_password.setLayoutX(305);
        icon_password.setLayoutY(305);
        icon_password.setFitHeight(20);
        icon_password.setFitWidth(20);

        PasswordField txtPassword = new PasswordField();
        txtPassword.setLayoutX(335);
        txtPassword.setLayoutY(300);
        txtPassword.setPrefWidth(250);
        txtPassword.setPrefHeight(35);
        txtPassword.setPromptText("Password");

        // Login-Button
        Button btnLogin = new Button("Anmelden");
        btnLogin.setLayoutX(335);
        btnLogin.setLayoutY(364);
        btnLogin.setPrefSize(250, 35);
        btnLogin.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            if (loginManager.isValidUser(username, password)) {
                onLoginSuccess.accept(username); // Callback-Funktion aufrufen
            } else {
                showLoginError();
            }
        });

        // Registrierungslink

        Label registerLabel = new Label("kein Account?");
        registerLabel.setLayoutX(366);
        registerLabel.setLayoutY(420);
        registerLabel.setStyle("-fx-text-fill: white");
        registerLabel.setId("lbl_info");


        Hyperlink registerLink = new Hyperlink("hier Registrieren");
        registerLink.setLayoutX(443);
        registerLink.setLayoutY(416);

        registerLink.setOnAction(e -> showRegisterWindow());

        // Alle Elemente hinzufügen
        loginPane.getChildren().addAll(lbl_titel, lbl_titel2, txtUsername, txtPassword, btnLogin, registerLink, icon_mail, icon_password, registerLabel);

        // Szene und Fenster anzeigen
        Scene loginScene = new Scene(loginPane);
        loginScene.getStylesheets().add(getClass().getResource("/ode/chatconnect_odeproject/style.css").toExternalForm());

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    /**
     * Zeigt eine Fehlermeldung bei ungültigen Anmeldedaten an.
     */

    private void showLoginError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login-Fehler");
        alert.setHeaderText("Ungültige Anmeldedaten");
        alert.setContentText("Bitte überprüfe deinen Benutzernamen und dein Passwort.");
        alert.showAndWait();
    }


    /**
     * Zeigt das Registrierungsfenster an und ermöglicht die Eingabe neuer Benutzerdaten.
     */

    private void showRegisterWindow() {
        Stage registerStage = new Stage();
        registerStage.setTitle("Registrieren");

        AnchorPane registerPane = new AnchorPane();
        registerPane.setPrefSize(900, 600);

        //Titel
        Label lbl_titel = new Label("Willkommen bei ChatConnect!");
        lbl_titel.setLayoutX(84);
        lbl_titel.setLayoutY(53);
        lbl_titel.setId("lbl_titel");

        Label lbl_titel2 = new Label("Bitte geben Sie Ihre Daten ein");
        lbl_titel2.setLayoutX(390);
        lbl_titel2.setLayoutY(131);
        lbl_titel2.setId("lbl_titel2");


        // Textfeld Name

        ImageView icon_mail2 = new ImageView("/ode/chatconnect_odeproject/mailIcon.png");
        icon_mail2.setLayoutX(305);
        icon_mail2.setLayoutY(240);
        icon_mail2.setFitHeight(20);
        icon_mail2.setFitWidth(20);


        TextField txtNewUsername = new TextField();
        txtNewUsername.setLayoutX(335);
        txtNewUsername.setLayoutY(233);
        txtNewUsername.setPrefWidth(250);
        txtNewUsername.setPrefHeight(35);
        txtNewUsername.setPromptText("Name");


        //Textfeld passwort

        ImageView icon_password2 = new ImageView("/ode/chatconnect_odeproject/passwordIcon.jpg");
        icon_password2.setLayoutX(305);
        icon_password2.setLayoutY(305);
        icon_password2.setFitHeight(20);
        icon_password2.setFitWidth(20);

        PasswordField txtNewPassword = new PasswordField();
        txtNewPassword.setLayoutX(335);
        txtNewPassword.setLayoutY(300);
        txtNewPassword.setPrefWidth(250);
        txtNewPassword.setPrefHeight(35);
        txtNewPassword.setPromptText("Passwort");


        //Registrierungsbutton

        Button btnRegisterConfirm = new Button("Registrieren");
        btnRegisterConfirm.setLayoutX(335);
        btnRegisterConfirm.setLayoutY(364);
        btnRegisterConfirm.setPrefSize(250, 35);
        btnRegisterConfirm.setOnAction(e -> loginManager.handleRegistration(
                txtNewUsername.getText(),
                txtNewPassword.getText(),
                registerStage,
                "users.txt"
        ));

        registerPane.getChildren().addAll(lbl_titel, lbl_titel2, txtNewUsername, txtNewPassword, btnRegisterConfirm, icon_password2, icon_mail2);

        Scene registerScene = new Scene(registerPane);
        registerScene.getStylesheets().add(getClass().getResource("/ode/chatconnect_odeproject/style.css").toExternalForm());


        registerStage.setScene(registerScene);
        registerStage.show();
    }
}
