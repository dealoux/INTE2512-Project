/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: Controller for manageCustomer-view, for admin to manage customers, one of the tabs of the main admin-view
*/

package ducle.videoStore.scenes;

import ducle.videoStore.user.customer.Customer;
import ducle.videoStore.user.customer.Guest;
import ducle.videoStore.user.customer.Regular;
import ducle.videoStore.user.customer.VIP;
import ducle.videoStore.StoreRepository;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageCustomerController {
    @FXML
    private TableView<Customer> cusTableAdmin;
    private ObservableList<Customer> customers;
    @FXML
    private Label manageCusOutput;
    @FXML
    private TableColumn<Customer, String> customerId;
    @FXML
    private TableColumn<Customer, String> customerName;
    @FXML
    private TableColumn<Customer, String> customerAddress;
    @FXML
    private TableColumn<Customer, String> customerPhone;
    @FXML
    private TableColumn<Customer, String> customerType;
    @FXML
    private TableColumn<Customer, String> customerRentalList;
    @FXML
    private TableColumn<Customer, String> customerUsername;
    @FXML
    private TableColumn<Customer, String> customerPassword;
    @FXML
    private TextField customerSearch;
    @FXML
    private Button customerAddButton;
    @FXML
    private Button customerUpdateButton;
    @FXML
    private Button customerDeleteButton;
    @FXML
    private ComboBox<String> customerDisplayComboBox;

    public void initialize(){
        // link the table cols to the items attributes
        customerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerType.setCellValueFactory(new PropertyValueFactory<>("type"));
        customerRentalList.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().printRentalList()));
        customerUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        customerPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        refreshCustomerTable();

        List<String> displayComboList = new ArrayList<>();
        displayComboList.add("All");
        displayComboList.addAll(Customer.getCustomerTypeList());
        customerDisplayComboBox.setItems(FXCollections.observableArrayList(displayComboList));
        customerDisplayComboBox.setValue(displayComboList.get(0));

        // Disable the delete button if nothing is selected
        customerDeleteButton.disableProperty().bind(Bindings.isNull(cusTableAdmin.getSelectionModel().selectedItemProperty()));
        customerUpdateButton.disableProperty().bind(Bindings.isNull(cusTableAdmin.getSelectionModel().selectedItemProperty()));
    }

    /**
     * This function creates a custom Customer search filter and apply it to the given ObservableList.
     * Used by the search bar
     * @param customers The Observablelist of customers to apply filter
     * */
    private void customerFilter(ObservableList<Customer> customers){
        FilteredList<Customer> filteredCustomers = new FilteredList<>(customers, b -> true);
        customerSearch.textProperty().addListener((observable, oldValue, newValue) -> {
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
                manageCusOutput.setText(StoreRepository.Instance().getUserManager().addCustomer(customer));
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

            Customer oldCopy = customer.createCopy();
            Optional<ButtonType> buttonHandler = dialog.showAndWait();

            if(buttonHandler.get() == ButtonType.OK){

                // handle class change if needed
                if(!customer.getClass().toString().equalsIgnoreCase(customer.getType())){
                    // add new type changed instance
                    switch (customer.getType()){
                        case "Regular":
                            StoreRepository.Instance().getUserManager().addRegular(new Regular(customer.getId(), customer.getName(), customer.getAddress(), customer.getPhone(), customer.getUsername(), customer.getPassword(), customer.getRentalMap()));
                            break;
                        case "VIP":
                            StoreRepository.Instance().getUserManager().addVip(new VIP(customer.getId(), customer.getName(), customer.getAddress(), customer.getPhone(), customer.getUsername(), customer.getPassword(), customer.getRentalMap()));
                            break;
                        case "Guest":
                            StoreRepository.Instance().getUserManager().addGuest(new Guest(customer.getId(), customer.getName(), customer.getAddress(), customer.getPhone(), customer.getUsername(), customer.getPassword(), customer.getRentalMap()));
                            break;
                    }
                    StoreRepository.Instance().getUserManager().removeCustomer(customer, true); // remove old instance
                }

                manageCusOutput.setText("Updated " + customer.getType() + " customer " + customer.getId());
            }
            else{
                StoreRepository.Instance().getUserManager().addCustomer(oldCopy);
            }

            refreshCustomerTable();
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    @FXML
    protected void onCusDeleteButton(ActionEvent event){
        Optional<ButtonType> confirmation = SceneUtilities.confirmationDialog(
                "Confirm delete",
                "Are you sure you would like to delete the selected customer?",
                "Any rented items in their inventory will be returned to the store\n");

        if(confirmation.get() == ButtonType.OK){
            Customer customer = cusTableAdmin.getSelectionModel().getSelectedItem();
            customers.remove(customer);
            manageCusOutput.setText(StoreRepository.Instance().getUserManager().removeCustomer(customer));
        }
    }

    @FXML
    protected void onCustomerDisplayComboBox(ActionEvent event){
        String result = "Displayed all ";

        switch (customerDisplayComboBox.getSelectionModel().getSelectedItem()){
            case "All":
                customerFilter(customers);
                result += "customers";
                break;
            case "Regular":
                customerFilter(FXCollections.observableArrayList(StoreRepository.Instance().getUserManager().getRegularList()));
                result += "regular customers";
                break;
            case "VIP":
                customerFilter(FXCollections.observableArrayList(StoreRepository.Instance().getUserManager().getVipList()));
                result += "VIP customers";
                break;
            case "Guest":
                customerFilter(FXCollections.observableArrayList(StoreRepository.Instance().getUserManager().getGuestList()));
                result += "guest customers";
                break;
        }

        manageCusOutput.setText(result);
    }

    private void refreshCustomerTable(){
        customers = FXCollections.observableArrayList(StoreRepository.Instance().getUserManager().getCustomerList());
        customerFilter(customers);
    }
}