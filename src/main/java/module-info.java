module ducle.videoStore {
    requires javafx.controls;
    requires javafx.fxml;


    opens ducle.videoStore to javafx.fxml;
    exports ducle.videoStore;
    exports ducle.videoStore.scenes;
    opens ducle.videoStore.scenes to javafx.fxml;
}