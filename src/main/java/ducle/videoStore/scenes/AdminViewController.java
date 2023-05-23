package ducle.videoStore.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminViewController {
    @FXML
    private BorderPane adminViewPane;

    @FXML
    protected void onLogoutAdminView (ActionEvent event) throws IOException {
        new SceneSwitch(adminViewPane, "scenes/store-view.fxml");
    }
}