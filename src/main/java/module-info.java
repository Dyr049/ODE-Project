module ode.chatconnect_odeproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;


    opens ode.chatconnect_odeproject to javafx.fxml;
    exports ode.chatconnect_odeproject;
    exports ode.chatconnect_odeproject.client;
    opens ode.chatconnect_odeproject.client to javafx.fxml;
    exports ode.chatconnect_odeproject.server;
    opens ode.chatconnect_odeproject.server to javafx.fxml;
    exports ode.chatconnect_odeproject.ui;
    opens ode.chatconnect_odeproject.ui to javafx.fxml;
}
