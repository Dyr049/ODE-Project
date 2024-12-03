package ode.chatconnect_odeproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;

public class ChatController {

    @FXML
    private Button btn_einstellungen;

    @FXML
    private Button btn_person1;

    @FXML
    private Button btn_person2;

    @FXML
    private Label lbl_personNameMessage;

    @FXML
    private AnchorPane pane_left;

    @FXML
    private AnchorPane pane_middle;

    @FXML
    private AnchorPane pane_right;

    @FXML
    private TextArea txt_area;

    @FXML
    private TextField txt_message;

    @FXML
    private TextField txt_search;


    //Funktion für die anpassung der Ansicht an Personen
    @FXML
    void personAuswahl1(ActionEvent event) {
        lbl_personNameMessage.setText(btn_person1.getText());
        //hier sollte noch die Nachrichten für diesen Chat geladen werden
    }

    //Funktion für die anpassung der Ansicht an Personen
    @FXML
    void personAuswahl2(ActionEvent event) {
        lbl_personNameMessage.setText(btn_person2.getText());
        //hier sollte noch die Nachrichten für diesen Chat geladen werden
    }

    @FXML
    void sucheStart(InputMethodEvent event) {

    }

    @FXML
    void swapToEinstellungen(ActionEvent event) {

    }

}
