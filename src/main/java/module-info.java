module ducle {
    requires javafx.controls;
    requires javafx.fxml;

    exports ducle.item;
    opens ducle.item to javafx.fxml;

    exports ducle.user;
    opens ducle.user to javafx.fxml;

    exports ducle.user.customer;
    opens ducle.user.customer to javafx.fxml;

    exports ducle.videoStore;
    opens ducle.videoStore to javafx.fxml;

    exports ducle.videoStore.scenes;
    opens ducle.videoStore.scenes to javafx.fxml;
}