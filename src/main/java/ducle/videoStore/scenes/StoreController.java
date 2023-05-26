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

import ducle.videoStore.user.Admin;
import ducle.videoStore.user.User;
import ducle.videoStore.user.customer.Customer;
import ducle.videoStore.StoreApplication;
import ducle.videoStore.StoreRepository;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    public void initialize(){
        ImageView logo1 = new ImageView(new Image(StoreApplication.class.getResource("pictures/logo1.jpg").toString()));
        ImageView logo2 = new ImageView(new Image(StoreApplication.class.getResource("pictures/logo2.jpg").toString()));
        logo1.setFitWidth(475);
        logo1.setFitHeight(650);
        logo2.setFitWidth(575);
        logo2.setFitHeight(650);
        storeViewPane.setLeft(logo1);
        storeViewPane.setRight(logo2);
    }

    private void loginErrorMsg(){
        loginOutputLabel.setText("Invalid Username or Password, please try again!");
    }

    @FXML
    protected void onLoginButton(ActionEvent event) throws IOException {
        String username = loginUsername.getText();
        String password = loginPassword.getText();

        if(!username.isEmpty() && !password.isEmpty()){
            User user = StoreRepository.Instance().getUserManager().searchUserByUsername(username);

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