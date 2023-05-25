package ducle.videoStore.scenes;

import ducle.item.Item;
import ducle.user.customer.Customer;
import ducle.videoStore.StoreRepository;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class AdminViewController {
    @FXML
    private BorderPane adminViewPane;

    public void initialize(){
        initItemTab();
        initCustomerTab();
    }

    /* Manage Items tab */
    @FXML
    private TableView<Item> itemTableAdmin;
    private ObservableList<Item> items;
    @FXML
    private Label manageItemOutput;
    @FXML
    private TableColumn<Item, String> itemIdAdmin;
    @FXML
    private TableColumn<Item, String> itemTitleAdmin;
    @FXML
    private TableColumn<Item, String> itemRentalTypeAdmin;
    @FXML
    private TableColumn<Item, String> itemGenreAdmin;
    @FXML
    private TableColumn<Item, String> itemLoanTypeAdmin;
    @FXML
    private TableColumn<Item, Integer> itemStockAdmin;
    @FXML
    private TableColumn<Item, String> itemFeeAdmin;
    @FXML
    private TableColumn<Item, String> itemStatusAdmin;
    @FXML
    private TextField itemSearchAdmin;
    @FXML
    private Button itemAddButton;
    @FXML
    private Button itemUpdateButton;
    @FXML
    private Button itemDeleteButton;
    @FXML
    private Button itemDisplayAllButton;
    @FXML
    private Button itemDisplayOOSButton;

    private void initItemTab(){
        items = FXCollections.observableArrayList(StoreRepository.getItemManager().getItemList());
        itemFilter(items);

        // link the table cols to the items attributes
        itemIdAdmin.setCellValueFactory(new PropertyValueFactory<>("id"));
        itemTitleAdmin.setCellValueFactory(new PropertyValueFactory<>("title"));
        itemStockAdmin.setCellValueFactory(new PropertyValueFactory<>("stock"));
        itemRentalTypeAdmin.setCellValueFactory(new PropertyValueFactory<>("rentalType"));
        itemGenreAdmin.setCellValueFactory(new PropertyValueFactory<>("genre"));
        itemLoanTypeAdmin.setCellValueFactory(new PropertyValueFactory<>("loanType"));
        itemFeeAdmin.setCellValueFactory(new PropertyValueFactory<>("fee"));
        itemStatusAdmin.setCellValueFactory(new PropertyValueFactory<>("rentalStatus"));

        // Disable the delete button if nothing is selected
        itemDeleteButton.disableProperty().bind(Bindings.isNull(itemTableAdmin.getSelectionModel().selectedItemProperty()));
        itemUpdateButton.disableProperty().bind(Bindings.isNull(itemTableAdmin.getSelectionModel().selectedItemProperty()));
    }

    private void itemFilter(ObservableList<Item> items){
        FilteredList<Item> filteredItems = new FilteredList<>(items, b -> true);
        itemSearchAdmin.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredItems.setPredicate(item -> {
                // No search keywords
                if(newValue.isBlank()){
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                // check for a match in item id
                if(item.getId().toLowerCase().contains(searchKeyword)){
                    return true;
                }
                // check for a match in item title
                else if(item.getTitle().toLowerCase().contains(searchKeyword)){
                    return true;
                }
                // check for a match in item rental type
                else if(item.getRentalType().toLowerCase().contains(searchKeyword)){
                    return true;
                }
                // check for a match in item genre
                else if(item.getGenre().toLowerCase().contains(searchKeyword)){
                    return true;
                }
                // no match is found
                else{
                    return false;
                }
            });
        });

        SortedList<Item> sortedItems = new SortedList<>(filteredItems);
        sortedItems.comparatorProperty().bind(itemTableAdmin.comparatorProperty());
        itemTableAdmin.setItems(sortedItems);
    }

    @FXML
    protected void onItemAddButton(ActionEvent event){
        Item item = new Item();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("item-editor.fxml"));
            DialogPane itemEditorPane = fxmlLoader.load();
            ItemEditorController itemEditorController = fxmlLoader.getController();

            itemEditorController.setItem(item);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(itemEditorPane);
            dialog.setTitle("Add Item");

            Optional<ButtonType> buttonHandler = dialog.showAndWait();

            if(buttonHandler.get() == ButtonType.OK){
                items.add(item);
                manageItemOutput.setText(StoreRepository.getItemManager().addItem(item));
            }
        } catch (IOException e ){
            System.out.println(e);
        }
    }
    @FXML
    protected void onItemUpdateButton(ActionEvent event){
        Item item = itemTableAdmin.getSelectionModel().getSelectedItem();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("item-editor.fxml"));
            DialogPane itemEditorPane = fxmlLoader.load();
            ItemEditorController itemEditorController = fxmlLoader.getController();

            itemEditorController.setItem(item);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(itemEditorPane);
            dialog.setTitle("Update Item");

            manageItemOutput.setText("Updated " + item.getRentalType() + " " + item.getId());

            dialog.showAndWait();
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    @FXML
    protected void onItemDeleteButton(ActionEvent event){
        Optional<ButtonType> confirmation = SceneUtilities.confirmationDialog(
                "Confirm delete",
                "Delete confirmation",
                "Are you sure you would like to delete the selected item?");

        if(confirmation.get() == ButtonType.OK){
            Item item = itemTableAdmin.getSelectionModel().getSelectedItem();
            items.remove(itemTableAdmin.getSelectionModel().getSelectedItem());
            manageItemOutput.setText(StoreRepository.getItemManager().removeItem(item));
        }
    }

    @FXML
    protected void onItemDisplayAllButton(ActionEvent event){
        itemFilter(items);
        manageItemOutput.setText("Displayed all items");
    }

    @FXML
    protected void onItemDisplayOOSButton(ActionEvent event){
        itemFilter(FXCollections.observableArrayList(StoreRepository.getItemManager().getOOSItemList()));
        manageItemOutput.setText("Displayed all out-of-stock items");
    }
    /* Ends of Manage Items tab */

    /* Manage Items tab */
    @FXML
    private TableView<Customer> cusTableAdmin;
    private ObservableList<Customer> customers;
    @FXML
    private Label manageCusOutput;
    @FXML
    private TableColumn<Item, String> cusIdAdmin;
    @FXML
    private TableColumn<Item, String> cusNameAdmin;
    @FXML
    private TableColumn<Item, String> cusAddressAdmin;
    @FXML
    private TableColumn<Item, String> cusPhoneAdmin;
    @FXML
    private TableColumn<Item, String> cusTypeAdmin;
    @FXML
    private TableColumn<Item, String> cusUsernameAdmin;
    @FXML
    private TableColumn<Item, String> cusPasswordAdmin;
    @FXML
    private TextField cusSearch;
    @FXML
    private Button cusAddButton;
    @FXML
    private Button cusUpdateButton;
    @FXML
    private Button cusDeleteButton;
    @FXML
    private Button cusDisplayAllButton;
    @FXML
    private Button cusDisplayGroupButton;

    private void initCustomerTab(){
        customers = FXCollections.observableArrayList(StoreRepository.getUserManager().getCustomerList());
        customerFilter(customers);

        // link the table cols to the items attributes
        cusIdAdmin.setCellValueFactory(new PropertyValueFactory<>("id"));
        cusNameAdmin.setCellValueFactory(new PropertyValueFactory<>("name"));
        cusAddressAdmin.setCellValueFactory(new PropertyValueFactory<>("address"));
        cusPhoneAdmin.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cusTypeAdmin.setCellValueFactory(new PropertyValueFactory<>("type"));
        cusUsernameAdmin.setCellValueFactory(new PropertyValueFactory<>("username"));
        cusPasswordAdmin.setCellValueFactory(new PropertyValueFactory<>("password"));

        // Disable the delete button if nothing is selected
        cusDeleteButton.disableProperty().bind(Bindings.isNull(cusTableAdmin.getSelectionModel().selectedItemProperty()));
        cusUpdateButton.disableProperty().bind(Bindings.isNull(cusTableAdmin.getSelectionModel().selectedItemProperty()));
    }

    private void customerFilter(ObservableList<Customer> customers){
        FilteredList<Customer> filteredCustomers = new FilteredList<>(customers, b -> true);
        cusSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCustomers.setPredicate(item -> {
                // No search keywords
                if(newValue.isBlank()){
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                // check for a match in customer id
                if(item.getId().toLowerCase().contains(searchKeyword)){
                    return true;
                }
                // check for a match in customer name
                else if(item.getName().toLowerCase().contains(searchKeyword)){
                    return true;
                }
                // check for a match in customer address
                else if(item.getAddress().toLowerCase().contains(searchKeyword)){
                    return true;
                }
                // check for a match in customer phone number
                else if(item.getPhone().toLowerCase().contains(searchKeyword)){
                    return true;
                }
                // check for a match in customer type
                else if(item.getType().toLowerCase().contains(searchKeyword)){
                    return true;
                }
                // check for a match in customer username
                else if(item.getUsername().toLowerCase().contains(searchKeyword)){
                    return true;
                }
                // no match is found
                else{
                    return false;
                }
            });
        });

        SortedList<Customer> sortedCustomers = new SortedList<>(filteredCustomers);
        sortedCustomers.comparatorProperty().bind(cusTableAdmin.comparatorProperty());
        cusTableAdmin.setItems(sortedCustomers);
    }

    @FXML
    protected void onCusAddButton(ActionEvent event){
        Customer customer = new Customer();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("user-editor.fxml"));
            DialogPane userEditorPane = fxmlLoader.load();
            UserEditorController userEditorController = fxmlLoader.getController();

            userEditorController.setUser(customer);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(userEditorPane);
            dialog.setTitle("Add Customer");

            Optional<ButtonType> buttonHandler = dialog.showAndWait();

            if(buttonHandler.get() == ButtonType.OK){
                customers.add(customer);
                manageCusOutput.setText(StoreRepository.getUserManager().addCustomer(customer));
            }
        } catch (IOException e ){
            System.out.println(e);
        }
    }
    @FXML
    protected void onCusUpdateButton(ActionEvent event){
        Customer customer = cusTableAdmin.getSelectionModel().getSelectedItem();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("user-editor.fxml"));
            DialogPane userEditorPane = fxmlLoader.load();
            UserEditorController userEditorController = fxmlLoader.getController();

            userEditorController.setUser(customer);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(userEditorPane);
            dialog.setTitle("Update Customer");

            manageCusOutput.setText("Updated " + customer.getType() + " customer " + customer.getId());

            dialog.showAndWait();
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    @FXML
    protected void onCusDeleteButton(ActionEvent event){
        Optional<ButtonType> confirmation = SceneUtilities.confirmationDialog(
                "Confirm delete",
                "Delete confirmation",
                "Are you sure you would like to delete the selected customer?\nAny items in the user inventory will be returned to the store");

        if(confirmation.get() == ButtonType.OK){
            Customer customer = cusTableAdmin.getSelectionModel().getSelectedItem();
            customers.remove(customer);
            manageCusOutput.setText(StoreRepository.getUserManager().removeCustomer(customer));
        }
    }

    @FXML
    protected void onCusDisplayAllButton(ActionEvent event){
        customerFilter(customers);
        manageCusOutput.setText("Displayed all customers");
    }

    @FXML
    protected void onCusDisplayGroupButton(ActionEvent event){
//        customerFilter(FXCollections.observableArrayList(StoreRepository.getItemManager().getOOSItemList()));
        manageCusOutput.setText("Displayed group of customers");
    }
    /* Ends of Manage Customers tab */

    @FXML
    protected void onLogoutAdminView (ActionEvent event) throws IOException {
        SceneUtilities.sceneSwitch(adminViewPane, "store-view.fxml");
    }
}