module com.example.employeemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.employeemanagementsystem to javafx.fxml;
    exports com.example.employeemanagementsystem;
}