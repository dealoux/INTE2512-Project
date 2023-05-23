package ducle.videoStore.scenes;

import ducle.videoStore.StoreApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitch {
    public SceneSwitch(BorderPane currentPane, String fxml) throws IOException {
        BorderPane nextPane = FXMLLoader.load(Objects.requireNonNull(StoreApplication.class.getResource(fxml)));
        currentPane.getChildren().removeAll();
        currentPane.getChildren().setAll(nextPane);
    }
}
