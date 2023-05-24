package ducle.videoStore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StoreApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StoreApplication.class.getResource("scenes/store-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Video Store");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        new StoreRepository(); // initialize the store database
        launch();
    }
}