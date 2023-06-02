/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: Controller for userProfile-view, grid pane layout for editable fields of a user
*/

package ducle.videoStore.scenes;

import ducle.videoStore.user.Admin;
import ducle.videoStore.user.User;
import ducle.videoStore.user.customer.Customer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class UserProfileController {
    @FXML
    protected GridPane userProfilePane;
    @FXML
    protected TextField userIdProfile;
    @FXML
    protected TextField userNameProfile;
    @FXML
    protected TextField userAddressProfile;
    @FXML
    protected TextField userPhoneProfile;
    @FXML
    protected ComboBox<String> userTypeProfile;
    @FXML
    protected TextField userUsernameProfile;
    @FXML
    protected PasswordField userPasswordProfile;
    protected String userId; // id reference to the current user


    /**
     * This function binds the given user attributes as the default values
     * @param user the user whose attributes be bind
     * */
    public void setUser(User user){
        userId = user.getId();
        if(user instanceof Admin){
            userTypeProfile.setItems(FXCollections.observableArrayList(User.getUserTypeList()));
        }
        else if(user instanceof Customer){
            userTypeProfile.setItems(FXCollections.observableArrayList(Customer.getCustomerTypeList()));
        }

        userIdProfile.textProperty().bindBidirectional(user.idProperty());
        userNameProfile.textProperty().bindBidirectional(user.nameProperty());
        userAddressProfile.textProperty().bindBidirectional(user.addressProperty());
        userPhoneProfile.textProperty().bindBidirectional(user.phoneProperty());
        userTypeProfile.valueProperty().bindBidirectional(user.typeProperty());
        userUsernameProfile.textProperty().bindBidirectional(user.usernameProperty());
        userPasswordProfile.textProperty().bindBidirectional(user.passwordProperty());
    }

    public void disableTypeSelection(){
        userTypeProfile.setDisable(true);
    }

    public void disableIDEditor(){
        userIdProfile.setDisable(true);
    }
}