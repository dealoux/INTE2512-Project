package ducle.videoStore.scenes;

import ducle.user.Admin;
import ducle.user.User;
import ducle.user.customer.Customer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UserEditorController {
    @FXML
    private DialogPane userEditorPane;
    @FXML
    private Label userEditorOutputLabel;
    @FXML
    private TextField userIdEditor;
    @FXML
    private TextField userNameEditor;
    @FXML
    private TextField userAddressEditor;
    @FXML
    private TextField userPhoneEditor;
    @FXML
    private ComboBox<String> userTypeEditor;
    @FXML
    private TextField userUsernameEditor;
    @FXML
    private PasswordField userPasswordEditor;

    public void initialize(){
//        userTypeEditor.setItems(FXCollections.observableArrayList(Customer.getCustomerTypeList()));
    }

    public void setUser(User user){
        if(user instanceof Admin){
            userTypeEditor.setItems(FXCollections.observableArrayList(User.getUserTypeList()));
        }
        else if(user instanceof Customer){
            userTypeEditor.setItems(FXCollections.observableArrayList(Customer.getCustomerTypeList()));
        }

        userIdEditor.textProperty().bindBidirectional(user.idProperty());
        userNameEditor.textProperty().bindBidirectional(user.nameProperty());
        userAddressEditor.textProperty().bindBidirectional(user.addressProperty());
        userPhoneEditor.textProperty().bindBidirectional(user.phoneProperty());
        userTypeEditor.valueProperty().bindBidirectional(user.typeProperty());
        userUsernameEditor.textProperty().bindBidirectional(user.usernameProperty());
        userPasswordEditor.textProperty().bindBidirectional(user.passwordProperty());
    }
}