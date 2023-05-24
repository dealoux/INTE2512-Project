package ducle.videoStore.scenes;

import ducle.item.Item;
import ducle.videoStore.StoreRepository;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminViewController {
    @FXML
    private BorderPane adminViewPane;

    public void initialize(){
        initItemTab();
    }

    @FXML
    protected void onLogoutAdminView (ActionEvent event) throws IOException {
        new SceneSwitch(adminViewPane, "scenes/store-view.fxml");
    }

    /* Manage Items */
    @FXML
    private TableView<Item> itemTableAdmin;
    private ObservableList<Item> items;
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
    private Button itemAddButton;
    @FXML
    private Button itemUpdateButton;
    @FXML
    private Button itemDeleteButton;

    private void initItemTab(){
        items = FXCollections.observableArrayList(StoreRepository.getItemManager().getItemList());
//        System.out.println(items);
        itemTableAdmin.setItems(items);

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
    }

    @FXML
    protected void onItemAddButton(ActionEvent event){

    }
    @FXML
    protected void onItemUpdateButton(ActionEvent event){

    }
    @FXML
    protected void onItemDeleteButton(ActionEvent event){

    }
}