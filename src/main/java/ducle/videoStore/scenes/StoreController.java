/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: Controller for store-view, main view when the program starts, login screen
*/

package ducle.videoStore.scenes;

import ducle.user.Admin;
import ducle.user.User;
import ducle.user.customer.Customer;
import ducle.videoStore.StoreRepository;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
                        try{
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(SceneUtilities.class.getResource("admin-view.fxml"));
                            BorderPane adminViewPane = fxmlLoader.load();
                            AdminController adminViewController = fxmlLoader.getController();
                            adminViewController.setUser(user);

                            SceneUtilities.sceneSwitch(storeViewPane, adminViewPane);
                        } catch (IOException e ){
                            System.out.println(e);
                        }
                    }
                    else if(user instanceof Customer){
                        try{
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(SceneUtilities.class.getResource("customer-view.fxml"));
                            BorderPane customerViewPane = fxmlLoader.load();
                            CustomerController customerViewController = fxmlLoader.getController();
                            customerViewController.setUser(user);

                            SceneUtilities.sceneSwitch(storeViewPane, customerViewPane);
                        } catch (IOException e ){
                            System.out.println(e);
                        }
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