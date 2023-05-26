/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: Controller for admin-view, main view when logged in as an admin, parent to 3 tabs: manageItem-view, manageCustomer-view, userProfile-view
*/

package ducle.videoStore.scenes;

import ducle.videoStore.user.User;
import ducle.videoStore.StoreRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminController {
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
            BorderPane profilePane = new BorderPane();
            profilePane.setTop(fxmlLoader.load());
            newTab.setContent(profilePane);
            userProfileController = fxmlLoader.getController();

            adminTabPane.getTabs().add(newTab);
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    public void setUser(User user){
        userProfileController.setUser(user);
        userProfileController.disableTypeSelection();
    }

    @FXML
    protected void onLogoutAdminView (ActionEvent event) throws IOException {
        SceneUtilities.sceneSwitch(adminViewPane, "store-view.fxml");
        StoreRepository.saveData();
    }
}