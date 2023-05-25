package ducle.videoStore.scenes;

import ducle.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class CustomerViewController {
    @FXML
    private BorderPane customerViewPane;
    @FXML
    private TabPane customerTabPane;
    private UserProfileController userProfileController;

    public void initialize(){
        SceneUtilities.addTab(customerTabPane,"manageItem-view.fxml", "Browse");

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(SceneUtilities.class.getResource("userProfile-view.fxml"));

            Tab newTab = new Tab("Profile");
            newTab.setContent(fxmlLoader.load());
            userProfileController = fxmlLoader.getController();

            customerTabPane.getTabs().add(newTab);
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    public void setUser(User user){
        userProfileController.setUser(user);
        userProfileController.disableTypeSelection();
    }

    @FXML
    protected void onLogoutCustomerView (ActionEvent event) throws IOException {
        SceneUtilities.sceneSwitch(customerViewPane, "store-view.fxml");
    }
}