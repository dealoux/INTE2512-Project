module ducle.videoStore {
    requires javafx.controls;
    requires javafx.fxml;


    exports ducle.item;
    opens ducle.item to javafx.fxml;

    exports ducle.user;
    opens ducle.user to javafx.fxml;

    exports ducle.user.customer;
    opens ducle.user.customer to javafx.fxml;

    opens ducle.videoStore to javafx.fxml;
    exports ducle.videoStore;

    exports ducle.videoStore.scenes;
    opens ducle.videoStore.scenes to javafx.fxml;
}