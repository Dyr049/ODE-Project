module ode.chatconnect_odeproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;


    opens ode.chatconnect_odeproject to javafx.fxml;
    exports ode.chatconnect_odeproject;
}
