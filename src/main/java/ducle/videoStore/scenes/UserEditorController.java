package ducle.videoStore.scenes;

import ducle.user.User;
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
            fxmlLoader.setLocation(getClass().getResource("user-profile.fxml"));
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