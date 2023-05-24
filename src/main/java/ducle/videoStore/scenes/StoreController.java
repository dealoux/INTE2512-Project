package ducle.videoStore.scenes;

import ducle.user.Admin;
import ducle.user.User;
import ducle.user.customer.Customer;
import ducle.videoStore.StoreRepository;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class StoreController {
    @FXML
    private BorderPane storeViewPane;
    @FXML
    private Label loginOutputLabel;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;

    private void loginErrorMsg(){
        loginOutputLabel.setText("Invalid Username or Password, please try again!");
    }

    @FXML
    protected void onLoginButton(ActionEvent event) throws IOException {
        String username = loginUsername.getText();
        String password = loginPassword.getText();

        if(!username.isEmpty() && !password.isEmpty()){
            User user = StoreRepository.getUserManager().searchUserByUsername(username);

            if(user == null){
                loginErrorMsg();
            }
            else{
                if(!user.getPassword().equals(password)){
                    loginOutputLabel.setText("Invalid Password, please try again!");
                }
                else{
                    if(user instanceof Admin){
                        new SceneSwitch(storeViewPane, "scenes/admin-view.fxml");
                    }
                    else if(user instanceof Customer){
                        new SceneSwitch(storeViewPane, "scenes/customer-view.fxml");
                    }
                }
            }
        }
        else{
            loginErrorMsg();
        }
    }

    @FXML
    protected void onLoginQuitButton(ActionEvent event){
        Platform.exit();
    }
}