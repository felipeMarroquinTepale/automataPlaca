module com.aplication.automataplaca {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.aplication.automataplaca to javafx.fxml;
    exports com.aplication.automataplaca;
}