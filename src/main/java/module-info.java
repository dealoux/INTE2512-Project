module ducle {
    requires javafx.controls;
    requires javafx.fxml;

    exports ducle.videoStore.item;
    opens ducle.videoStore.item to javafx.fxml;

    exports ducle.videoStore.user;
    opens ducle.videoStore.user to javafx.fxml;

    exports ducle.videoStore.user.customer;
    opens ducle.videoStore.user.customer to javafx.fxml;

    exports ducle.videoStore;
    opens ducle.videoStore to javafx.fxml;

    exports ducle.videoStore.scenes;
    opens ducle.videoStore.scenes to javafx.fxml;
    exports ducle.videoStore.managers;
    opens ducle.videoStore.managers to javafx.fxml;
}