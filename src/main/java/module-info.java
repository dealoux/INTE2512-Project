module ducle.videostore {
    requires javafx.controls;
    requires javafx.fxml;


    opens ducle.videostore to javafx.fxml;
    exports ducle.videostore;
}