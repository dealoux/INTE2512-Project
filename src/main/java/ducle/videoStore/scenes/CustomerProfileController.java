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

import ducle.videoStore.StoreRepository;
import ducle.videoStore.user.customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.text.NumberFormat;

public class CustomerProfileController extends UserProfileController {
    @FXML
    protected TextField customerRentals;
    @FXML
    protected TextField customerReturns;
    @FXML
    protected TextField customerRewards;

    public void setUser(Customer customer){
        super.setUser(customer);

        customerRentals.textProperty().bindBidirectional(customer.getStats().rentCountProperty(), NumberFormat.getCompactNumberInstance());
        customerReturns.textProperty().bindBidirectional(customer.getStats().returnCountProperty(), NumberFormat.getCompactNumberInstance());
        customerRewards.textProperty().bindBidirectional(customer.getStats().rewardPointsProperty(), NumberFormat.getCompactNumberInstance());
        customerRentals.setDisable(true);
        customerReturns.setDisable(true);
        customerRewards.setDisable(true);
    }

    public void refresh(){
        setUser(StoreRepository.Instance().getCustomerManager().search(userId));
    }
}