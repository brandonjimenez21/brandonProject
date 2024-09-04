module com.example.brandonproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.brandonproject to javafx.fxml;
    exports com.example.brandonproject;
}