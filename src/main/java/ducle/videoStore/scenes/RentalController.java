package ducle.videoStore.scenes;

import ducle.item.Item;
import ducle.user.customer.Customer;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RentalController {
    @FXML
    private VBox rentalPane;
    @FXML
    private Label rentalOutput;
    private Customer customer;

    @FXML
    private BorderPane browseStorePane;
    private TableView<Item> itemTableStore;
    private ObservableList<Item> itemsStore;
    @FXML
    private TextField itemSearchStore;
    @FXML
    private Button rentButton;
    @FXML
    private Button browseStoreDisplayAllButton;
    @FXML
    private Button browseStoreDisplayISButton;

    @FXML
    private BorderPane browseInventoryPane;
    @FXML
    private TableView<Item> itemTableInventory;
    private ObservableList<Item> itemsInventory;
    @FXML
    private TextField itemSearchInventory;
    @FXML
    private Button returnButton;
    @FXML
    private Button returnMultipleButton;
    @FXML
    private Button returnAllButton;

    @FXML
    private TableColumn<Item, String> itemIdInventory;
    @FXML
    private TableColumn<Item, String> itemTitleInventory;
    @FXML
    private TableColumn<Item, String> itemRentalTypeInventory;
    @FXML
    private TableColumn<Item, String> itemGenreInventory;
    @FXML
    private TableColumn<Item, String> itemLoanTypeInventory;
    @FXML
    private TableColumn<Item, Integer> itemQuantity;

    @FXML
    public void initialize(){
        initBrowseStoreSection();
        initBrowseInventorySection();
    }

    private void initBrowseStoreSection(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemTable-view.fxml"));
            itemTableStore = fxmlLoader.load();
            browseStorePane.setCenter(itemTableStore);

        } catch (IOException e ){
            System.out.println(e);
        }

        // Disable the delete button if nothing is selected
        rentButton.disableProperty().bind(Bindings.isNull(itemTableStore.getSelectionModel().selectedItemProperty()));
    }

    private void initBrowseInventorySection(){
        itemIdInventory.setCellValueFactory(new PropertyValueFactory<>("id"));
        itemTitleInventory.setCellValueFactory(new PropertyValueFactory<>("title"));
        itemRentalTypeInventory.setCellValueFactory(new PropertyValueFactory<>("rentalType"));
        itemGenreInventory.setCellValueFactory(new PropertyValueFactory<>("genre"));
        itemLoanTypeInventory.setCellValueFactory(new PropertyValueFactory<>("loanType"));
        itemQuantity.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Disable the return/return multiple buttons if nothing is selected
        returnButton.disableProperty().bind(Bindings.isNull(itemTableInventory.getSelectionModel().selectedItemProperty()));
        returnMultipleButton.disableProperty().bind(Bindings.isNull(itemTableInventory.getSelectionModel().selectedItemProperty()));
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        refreshStoreTable();
        refreshInventoryTable();
    }

    public void refreshStoreTable(){
        itemsStore = SceneUtilities.getObsItemList();
        SceneUtilities.itemFilter(itemsStore, itemSearchStore, itemTableStore);
    }

    public void refreshInventoryTable(){
        itemsInventory = FXCollections.observableArrayList(customer.getRentalList());
        SceneUtilities.itemFilter(itemsInventory, itemSearchInventory, itemTableInventory);
    }

    @FXML
    protected void onRentButton(ActionEvent event){
        Item item = itemTableStore.getSelectionModel().getSelectedItem();

        rentalOutput.setText(customer.rent(item));
        refreshInventoryTable();
    }

    @FXML
    protected void onBrowseStoreDisplayAllButton(ActionEvent event){
        SceneUtilities.itemFilter(itemsStore, itemSearchStore, itemTableStore);
        rentalOutput.setText("Displayed all items");
    }

    @FXML
    protected void onBrowseStoreDisplayISButton(ActionEvent event){
        SceneUtilities.itemFilter(SceneUtilities.getObsISItemList(), itemSearchStore, itemTableStore);
        rentalOutput.setText("Displayed all in-stock items");
    }

    @FXML
    protected void onReturnButton(ActionEvent event){
        Item item = itemTableInventory.getSelectionModel().getSelectedItem();

        rentalOutput.setText(customer.returnItem(item));
        refreshInventoryTable();
    }

    @FXML
    protected void onReturnMultipleButton(ActionEvent event){
        Item item = itemTableInventory.getSelectionModel().getSelectedItem();
        rentalOutput.setText(customer.returnItemMultiple(item));
        refreshInventoryTable();
    }

    @FXML
    protected void onReturnAllButton(ActionEvent event){
        rentalOutput.setText(customer.returnAllItem());
        refreshInventoryTable();
    }
}