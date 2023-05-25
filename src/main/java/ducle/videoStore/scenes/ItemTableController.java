package ducle.videoStore.scenes;

import ducle.item.Item;
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