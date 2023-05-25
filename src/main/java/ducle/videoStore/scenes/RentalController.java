package ducle.videoStore.scenes;


import ducle.user.customer.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class RentalController {
    @FXML
    private VBox rentalPane;
    private BorderPane browseStorePane;
    private BorderPane browseInventoryPane;
    private BrowseStoreController browseStoreController;
    private BrowseInventoryController browseInventoryController;
    private Customer customer;


    public void initialize(){
        initBrowseStoreSection();
        initBrowseInventorySection();
    }

    private void initBrowseStoreSection(){
        rentalPane.getChildren().add(new Text("Store"));

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("browseStore-view.fxml"));
            browseStorePane = fxmlLoader.load();
            browseStoreController = fxmlLoader.getController();

            rentalPane.getChildren().add(browseStorePane);
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    private void initBrowseInventorySection(){
        rentalPane.getChildren().add(new Text("Inventory"));

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("browseInventory-view.fxml"));
            browseInventoryPane = fxmlLoader.load();
            browseInventoryController = fxmlLoader.getController();

            rentalPane.getChildren().add(browseInventoryPane);
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        browseStoreController.setCustomer(customer);
        browseInventoryController.setCustomer(customer);
    }
}