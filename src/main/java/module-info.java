module org.example.apex {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires java.desktop;


    opens org.example.apex to javafx.fxml, junit;
    exports org.example.apex.test;
    opens org.example.apex.controllers;
    exports org.example.apex;
    opens org.example.apex.utils.list_objects;
    exports org.example.apex.exceptions;
    opens org.example.apex.exceptions to javafx.fxml, junit;
}