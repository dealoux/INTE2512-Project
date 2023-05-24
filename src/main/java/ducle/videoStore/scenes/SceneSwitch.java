package ducle.videoStore.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitch {
    public SceneSwitch(BorderPane currentPane, String fxml) throws IOException {
        BorderPane nextPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        currentPane.getChildren().removeAll();
        currentPane.getChildren().setAll(nextPane);
    }
}
