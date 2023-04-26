module com.example.autofillsearch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.autofillsearch to javafx.fxml;
    exports com.example.autofillsearch;
}