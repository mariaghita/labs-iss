module lab5.gestiunebug.main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.datatransfer;
    requires java.desktop;
    requires org.apache.logging.log4j;

    opens gestiunebug to javafx.fxml;
    exports gestiunebug;
    opens gestiunebug.model to javafx.fxml;
    exports gestiunebug.model;
    opens gestiunebug.controller to javafx.fxml;
    exports gestiunebug.controller;
}