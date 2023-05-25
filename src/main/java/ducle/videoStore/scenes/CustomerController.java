package ducle.videoStore.scenes;

import ducle.user.User;
import ducle.user.customer.Customer;
import ducle.videoStore.StoreRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class CustomerController {
    @FXML
    private BorderPane customerViewPane;
    @FXML
    private TabPane customerTabPane;
    private UserProfileController userProfileController;
    private RentalController rentalController;

    public void initialize(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(SceneUtilities.class.getResource("rental-view.fxml"));

            Tab newTab = new Tab("Rental");
            newTab.setContent(fxmlLoader.load());
            rentalController = fxmlLoader.getController();

            customerTabPane.getTabs().add(newTab);
        } catch (IOException e ){
            System.out.println(e);
        }

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(SceneUtilities.class.getResource("userProfile-view.fxml"));

            Tab newTab = new Tab("Profile");
            newTab.setContent(fxmlLoader.load());
            userProfileController = fxmlLoader.getController();

            customerTabPane.getTabs().add(newTab);
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    public void setUser(User user){
        userProfileController.setUser(user);
        userProfileController.disableTypeSelection();
        rentalController.setCustomer((Customer) user);
    }

    @FXML
    protected void onLogoutCustomerView (ActionEvent event) throws IOException {
        SceneUtilities.sceneSwitch(customerViewPane, "store-view.fxml");
        StoreRepository.saveData();
    }
}