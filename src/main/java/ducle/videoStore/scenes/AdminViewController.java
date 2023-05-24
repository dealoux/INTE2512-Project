package ducle.videoStore.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminViewController {
    @FXML
    private BorderPane adminViewPane;

    @FXML
    protected void onLogoutAdminView (ActionEvent event) throws IOException {
        new SceneSwitch(adminViewPane, "scenes/store-view.fxml");
    }

    /* Manage Items */
    @FXML
    private Button itemAddButton;
    @FXML
    private Button itemUpdateButton;
    @FXML
    private Button itemDeleteButton;

    @FXML
    protected void onItemAddButton(ActionEvent event){

    }
    @FXML
    protected void onItemUpdateButton(ActionEvent event){

    }
    @FXML
    protected void onItemDeleteButton(ActionEvent event){

    }
}