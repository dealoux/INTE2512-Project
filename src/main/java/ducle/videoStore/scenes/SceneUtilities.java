package ducle.videoStore.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class SceneUtilities {
    public static void sceneSwitch(BorderPane currentPane, String fxml) throws IOException {
        BorderPane nextPane = FXMLLoader.load(Objects.requireNonNull(SceneUtilities.class.getResource(fxml)));
        currentPane.getChildren().removeAll();
        currentPane.getChildren().setAll(nextPane);
    }

    public static Optional<ButtonType> confirmationDialog(String title, String header, String context){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        return alert.showAndWait();
    }
}
