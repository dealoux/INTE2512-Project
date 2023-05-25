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

import java.io.IOException;
import java.util.Optional;

public class ManageCustomerViewController {
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

    public void initialize(){
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
            fxmlLoader.setLocation(getClass().getResource("userEditor-view.fxml"));
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
            fxmlLoader.setLocation(getClass().getResource("userEditor-view.fxml"));
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
}