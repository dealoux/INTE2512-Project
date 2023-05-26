/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: Controller for itemTable-view, table of items with bindings, used in: manageItem-view, rental-view
*/

package ducle.videoStore.scenes;

import ducle.videoStore.item.Item;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ItemTableController {
    @FXML
    private TableView<Item> itemTableStore;
    @FXML
    private TableColumn<Item, String> itemIdStore;
    @FXML
    private TableColumn<Item, String> itemTitleStore;
    @FXML
    private TableColumn<Item, String> itemRentalTypeStore;
    @FXML
    private TableColumn<Item, String> itemGenreStore;
    @FXML
    private TableColumn<Item, String> itemLoanTypeStore;
    @FXML
    private TableColumn<Item, Integer> itemStockStore;
    @FXML
    private TableColumn<Item, String> itemFeeStore;
    @FXML
    private TableColumn<Item, String> itemStatusStore;

    public void initialize(){
        // link the table cols to the items attributes
        itemIdStore.setCellValueFactory(new PropertyValueFactory<>("id"));
        itemTitleStore.setCellValueFactory(new PropertyValueFactory<>("title"));
        itemRentalTypeStore.setCellValueFactory(new PropertyValueFactory<>("rentalType"));
        itemGenreStore.setCellValueFactory(new PropertyValueFactory<>("genre"));
        itemLoanTypeStore.setCellValueFactory(new PropertyValueFactory<>("loanType"));
        itemStockStore.setCellValueFactory(new PropertyValueFactory<>("stock"));
        itemFeeStore.setCellValueFactory(new PropertyValueFactory<>("fee"));
        itemStatusStore.setCellValueFactory(new PropertyValueFactory<>("rentalStatus"));
    }
}