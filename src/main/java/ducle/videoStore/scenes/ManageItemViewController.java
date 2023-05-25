package ducle.videoStore.scenes;

import ducle.item.Item;
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

public class ManageItemViewController {
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

    public void initialize(){
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
            fxmlLoader.setLocation(getClass().getResource("itemEditor-view.fxml"));
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
            fxmlLoader.setLocation(getClass().getResource("itemEditor-view.fxml"));
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
}