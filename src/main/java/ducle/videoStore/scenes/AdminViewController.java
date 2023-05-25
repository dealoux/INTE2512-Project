package ducle.videoStore.scenes;

import ducle.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminViewController {
    @FXML
    private BorderPane adminViewPane;
    @FXML
    private TabPane adminTabPane;
    private UserProfileController userProfileController;

    public void initialize(){
        SceneUtilities.addTab(adminTabPane,"manageItem-view.fxml", "Manage Items");
        SceneUtilities.addTab(adminTabPane,"manageCustomer-view.fxml", "Manage Customers");

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(SceneUtilities.class.getResource("userProfile-view.fxml"));

            Tab newTab = new Tab("Profile");
            newTab.setContent(fxmlLoader.load());
            userProfileController = fxmlLoader.getController();

            adminTabPane.getTabs().add(newTab);
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    @FXML
    protected void onLogoutAdminView (ActionEvent event) throws IOException {
        SceneUtilities.sceneSwitch(adminViewPane, "store-view.fxml");
    }

    public void setUser(User user){
        userProfileController.setUser(user);
    }
}