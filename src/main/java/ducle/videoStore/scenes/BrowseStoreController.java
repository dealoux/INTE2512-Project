package ducle.videoStore.scenes;

import ducle.item.Item;
import ducle.user.customer.Customer;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BrowseStoreController {
    @FXML
    private BorderPane browseStorePane;
    private TableView<Item> itemTableStore;
    private ObservableList<Item> items;
    private Customer customer;
    @FXML
    private Label browseStoreOutput;
    @FXML
    private TextField itemSearchStore;
    @FXML
    private Button rentButton;
    @FXML
    private Button browseStoreDisplayAllButton;
    @FXML
    private Button browseStoreDisplayISButton;

    public void initialize(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemTable-view.fxml"));
            itemTableStore = fxmlLoader.load();
            browseStorePane.setCenter(itemTableStore);

        } catch (IOException e ){
            System.out.println(e);
        }

        items = SceneUtilities.getObsItemList();
        SceneUtilities.itemFilter(items, itemSearchStore, itemTableStore);

        // Disable the delete button if nothing is selected
        rentButton.disableProperty().bind(Bindings.isNull(itemTableStore.getSelectionModel().selectedItemProperty()));
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void refreshTable(){
        items = SceneUtilities.getObsItemList();
        SceneUtilities.itemFilter(items, itemSearchStore, itemTableStore);
    }

    @FXML
    protected void onRentButton(ActionEvent event){
        Item item = itemTableStore.getSelectionModel().getSelectedItem();

        browseStoreOutput.setText(customer.rent(item));

    }

    @FXML
    protected void onBrowseStoreDisplayAllButton(ActionEvent event){
        SceneUtilities.itemFilter(items, itemSearchStore, itemTableStore);
        browseStoreOutput.setText("Displayed all items");
    }

    @FXML
    protected void onBrowseStoreDisplayISButton(ActionEvent event){
        SceneUtilities.itemFilter(SceneUtilities.getObsISItemList(), itemSearchStore, itemTableStore);
        browseStoreOutput.setText("Displayed all in-stock items");
    }
}