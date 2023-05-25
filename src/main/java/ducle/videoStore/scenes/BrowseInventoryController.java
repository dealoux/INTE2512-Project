package ducle.videoStore.scenes;

import ducle.item.Item;
import ducle.user.customer.Customer;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class BrowseInventoryController {
    @FXML
    private BorderPane browseInventoryPane;
    @FXML
    private TableView<Item> itemTableInventory;
    private ObservableList<Item> items;
    private Customer customer;
    @FXML
    private Label browseInventoryOutput;
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

    public void initialize(){
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
        refreshTable();
    }

    public void refreshTable(){
        items = FXCollections.observableArrayList(customer.getRentalList());
        SceneUtilities.itemFilter(items, itemSearchInventory, itemTableInventory);
    }

    @FXML
    protected void onReturnButton(ActionEvent event){
        Item item = itemTableInventory.getSelectionModel().getSelectedItem();

        browseInventoryOutput.setText(customer.returnItem(item));
        refreshTable();
    }

    @FXML
    protected void onReturnMultipleButton(ActionEvent event){
        Item item = itemTableInventory.getSelectionModel().getSelectedItem();
        browseInventoryOutput.setText(customer.returnItemMultiple(item));
        refreshTable();
    }

    @FXML
    protected void onReturnAllButton(ActionEvent event){
        browseInventoryOutput.setText(customer.returnAllItem());
        refreshTable();
    }
}