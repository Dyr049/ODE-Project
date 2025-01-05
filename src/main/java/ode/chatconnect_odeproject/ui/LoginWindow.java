package ode.chatconnect_odeproject.ui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ode.chatconnect_odeproject.client.LoginManager;

import ode.chatconnect_odeproject.client.*;
import ode.chatconnect_odeproject.server.*;

import java.util.function.Consumer;

public class LoginWindow {

    private final LoginManager loginManager;
    private final Consumer<String> onLoginSuccess;

    public LoginWindow(LoginManager loginManager, Consumer<String> onLoginSuccess) {
        this.loginManager = loginManager;
        this.onLoginSuccess = onLoginSuccess;
    }

    public void show(Stage primaryStage) {
        // Layout für das Login-Fenster erstellen
        AnchorPane loginPane = new AnchorPane();
        loginPane.setPrefSize(300, 250);

        // Username-Label und Textfeld
        Label lblUsername = new Label("Username:");
        lblUsername.setLayoutX(50);
        lblUsername.setLayoutY(40);

        TextField txtUsername = new TextField();
        txtUsername.setLayoutX(50);
        txtUsername.setLayoutY(70);
        txtUsername.setPrefWidth(200);

        // Password-Label und Passwortfeld
        Label lblPassword = new Label("Password:");
        lblPassword.setLayoutX(50);
        lblPassword.setLayoutY(100);

        PasswordField txtPassword = new PasswordField();
        txtPassword.setLayoutX(50);
        txtPassword.setLayoutY(130);
        txtPassword.setPrefWidth(200);

        // Login-Button
        Button btnLogin = new Button("Login");
        btnLogin.setLayoutX(50);
        btnLogin.setLayoutY(180);
        btnLogin.setOnAction(e -> {
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            if (loginManager.isValidUser(username, password)) {
                onLoginSuccess.accept(username); // Callback-Funktion aufrufen
            } else {
                showLoginError();
            }
        });

        // Registrieren-Button
        Button btnRegister = new Button("Registrieren");
        btnRegister.setLayoutX(150);
        btnRegister.setLayoutY(180);
        btnRegister.setOnAction(e -> showRegisterWindow());

        // Alle Elemente hinzufügen
        loginPane.getChildren().addAll(lblUsername, txtUsername, lblPassword, txtPassword, btnLogin, btnRegister);

        // Szene und Fenster anzeigen
        Scene loginScene = new Scene(loginPane);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    private void showLoginError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login-Fehler");
        alert.setHeaderText("Ungültige Anmeldedaten");
        alert.setContentText("Bitte überprüfe deinen Benutzernamen und dein Passwort.");
        alert.showAndWait();
    }

    private void showRegisterWindow() {
        Stage registerStage = new Stage();
        registerStage.setTitle("Registrieren");

        AnchorPane registerPane = new AnchorPane();
        registerPane.setPrefSize(300, 200);

        Label lblNewUsername = new Label("Neuer Benutzername:");
        lblNewUsername.setLayoutX(50);
        lblNewUsername.setLayoutY(40);

        TextField txtNewUsername = new TextField();
        txtNewUsername.setLayoutX(50);
        txtNewUsername.setLayoutY(70);
        txtNewUsername.setPrefWidth(200);

        Label lblNewPassword = new Label("Neues Passwort:");
        lblNewPassword.setLayoutX(50);
        lblNewPassword.setLayoutY(100);

        PasswordField txtNewPassword = new PasswordField();
        txtNewPassword.setLayoutX(50);
        txtNewPassword.setLayoutY(130);
        txtNewPassword.setPrefWidth(200);

        Button btnRegisterConfirm = new Button("Registrieren");
        btnRegisterConfirm.setLayoutX(100);
        btnRegisterConfirm.setLayoutY(160);
        btnRegisterConfirm.setOnAction(e -> loginManager.handleRegistration(
                txtNewUsername.getText(),
                txtNewPassword.getText(),
                registerStage,
                "users.txt"
        ));

        registerPane.getChildren().addAll(lblNewUsername, txtNewUsername, lblNewPassword, txtNewPassword, btnRegisterConfirm);

        Scene registerScene = new Scene(registerPane);
        registerStage.setScene(registerScene);
        registerStage.show();
    }
}
