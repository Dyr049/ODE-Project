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
    public AnchorPane anchorPane_person1;

    @FXML
    public AnchorPane anchorPane_person2;

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

    // Extra Funktion für die Suche um die Suche Übersichtlicher zu gestalten
    private void setVisibility(boolean person1Visibility, boolean person2Visibility){
        anchorPane_person1.setVisible(person1Visibility);
        anchorPane_person2.setVisible(person2Visibility);
    }


    // Die Suchfunktion: Mit einem addListener wird das Textfeld beobachtet und sobald der Inhalt mit einem der Namen der Personen übereinstimmt werden die Sichtbarkeiten aktualisiert
    // dabei ist die Groß & Kleinschreibung egal
    @FXML
    void sucheStart(ActionEvent event) {

        txt_search.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.equalsIgnoreCase(btn_person1.getText())){
                setVisibility(true, false);
            } else if (newValue.equalsIgnoreCase(btn_person2.getText())){
                setVisibility(false, true);
            } else {
                setVisibility(true, true);
            }
        });

    }

    @FXML
    void swapToEinstellungen(ActionEvent event) {

    }

}
