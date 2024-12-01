package ode.chatconnect_odeproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    public AnchorPane anchorPane_right;

    @FXML
    public AnchorPane anchorPane_left;

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

}
