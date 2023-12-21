module cs3500.pa05 {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.bootstrapicons;

    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

  opens cs3500.pa05 to javafx.fxml;
    exports cs3500.pa05;
    exports cs3500.pa05.controller;
    exports cs3500.pa05.model;
    exports cs3500.pa05.view;
    opens cs3500.pa05.controller to javafx.fxml;
  opens cs3500.pa05.model to javafx.fxml;
  exports cs3500.pa05.model.json;
  opens cs3500.pa05.model.json to com.fasterxml.jackson.databind, javafx.fxml;
}