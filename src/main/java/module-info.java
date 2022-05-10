module com.tp.angloie {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.tp.angloie to javafx.fxml;
    exports com.tp.angloie;
}