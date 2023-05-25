package ducle.videoStore.scenes;

import ducle.user.Admin;
import ducle.user.User;
import ducle.user.customer.Customer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class UserProfileController {
    @FXML
    private GridPane userProfilePane;
    @FXML
    private TextField userIdProfile;
    @FXML
    private TextField userNameProfile;
    @FXML
    private TextField userAddressProfile;
    @FXML
    private TextField userPhoneProfile;
    @FXML
    private ComboBox<String> userTypeProfile;
    @FXML
    private TextField userUsernameProfile;
    @FXML
    private PasswordField userPasswordProfile;

    public void initialize(){
//        userTypeEditor.setItems(FXCollections.observableArrayList(Customer.getCustomerTypeList()));
    }

    public void setUser(User user){
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
}