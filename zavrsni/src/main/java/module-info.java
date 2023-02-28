module com.example.zavrsni {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;


    opens com.example.zavrsni to javafx.fxml;
    exports com.example.zavrsni;
}