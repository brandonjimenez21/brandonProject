module com.example.brandonProject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.brandonProject to javafx.fxml;
    opens com.example.brandonProject.controllers to javafx.fxml;
    exports com.example.brandonProject;
}