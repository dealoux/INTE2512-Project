/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: Controller for userEditor-view, dialog pop up pane for adding/updating users
*/

package ducle.videoStore.scenes;

import ducle.videoStore.user.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import java.io.IOException;

public class UserEditorController {
    @FXML
    private DialogPane userEditorPane;
    private UserProfileController userProfileController;
    @FXML
    private Label userEditorOutputLabel;
    @FXML
    public void initialize(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("userProfile-view.fxml"));
            userEditorPane.setContent(fxmlLoader.load());
            userProfileController = fxmlLoader.getController();
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    public void setUser(User user){
        userProfileController.setUser(user);
    }
}