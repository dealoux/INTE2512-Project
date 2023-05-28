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

import ducle.videoStore.user.customer.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.Optional;

public class CustomerEditorController {
    @FXML
    private DialogPane customerEditorPane;
    private UserProfileController userProfileController;
    @FXML
    private Label customerEditorOutputLabel;
    private Customer customer;

    @FXML
    public void initialize(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("userProfile-view.fxml"));
            customerEditorPane.setContent(fxmlLoader.load());
            userProfileController = fxmlLoader.getController();
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
        userProfileController.setUser(customer);

        Button okButton = (Button) customerEditorPane.lookupButton(ButtonType.OK);
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            if(!customer.validId(customer.getId())){
                event.consume();

                customerEditorOutputLabel.setText("Invalid ID input");

                Optional<ButtonType> warning = SceneUtilities.warningDialog(
                        "Warning",
                        "Invalid ID input",
                        "Please provide a correct ID with the following format Cxxx");
            }
        });
    }
}