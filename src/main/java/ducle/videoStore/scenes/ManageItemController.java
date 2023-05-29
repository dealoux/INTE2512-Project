/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: Controller for manageItems-view, for admin to manage items, one of the tabs of the main admin-view
*/

package ducle.videoStore.scenes;

import ducle.videoStore.item.Item;
import ducle.videoStore.StoreRepository;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ManageItemController {
    @FXML
    private BorderPane manageItemPane;
    private TableView<Item> itemTableAdmin;
    private ObservableList<Item> items;
    @FXML
    private Label manageItemOutput;
    @FXML
    private TextField itemSearchAdmin;
    @FXML
    private Button itemAddButton;
    @FXML
    private Button itemUpdateButton;
    @FXML
    private Button itemDeleteButton;
    @FXML
    private ComboBox<String> itemDisplayComboBox;

    private List<String> displayComboList = new ArrayList<>(Arrays.asList("All", "Out-of-stock", "In-stock"));

    public void initialize(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemTable-view.fxml"));
            itemTableAdmin = fxmlLoader.load();
            manageItemPane.setCenter(itemTableAdmin);

        } catch (IOException e ){
            System.out.println(e);
        }

        refreshItemTable();

        itemDisplayComboBox.setItems(FXCollections.observableArrayList(displayComboList));
        itemDisplayComboBox.setValue("All");

        // Disable the delete, update buttons if nothing is selected
        itemDeleteButton.disableProperty().bind(Bindings.isNull(itemTableAdmin.getSelectionModel().selectedItemProperty()));
        itemUpdateButton.disableProperty().bind(Bindings.isNull(itemTableAdmin.getSelectionModel().selectedItemProperty()));
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
                item.validate();
                items.add(item);
                manageItemOutput.setText(StoreRepository.Instance().getItemManager().addItem(item));
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

            Item oldCopy = item.createCopy();
            Optional<ButtonType> buttonHandler = dialog.showAndWait();

            if(buttonHandler.get() == ButtonType.OK){
                item.validate();
                StoreRepository.Instance().getItemManager().validateMaps();
                manageItemOutput.setText("Updated " + item.getRentalType() + " " + item.getId());
            }
            else{
                StoreRepository.Instance().getItemManager().addItem(oldCopy);
            }

            refreshItemTable();
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    @FXML
    protected void onItemDeleteButton(ActionEvent event){
        Optional<ButtonType> confirmation = SceneUtilities.confirmationDialog(
                "Confirm delete",
                "Are you sure you would like to delete the selected item?",
                "");

        if(confirmation.get() == ButtonType.OK){
            Item item = itemTableAdmin.getSelectionModel().getSelectedItem();
            items.remove(itemTableAdmin.getSelectionModel().getSelectedItem());
            manageItemOutput.setText(StoreRepository.Instance().getItemManager().removeItem(item));
        }
    }

    @FXML
    protected void onItemDisplayComboBox(ActionEvent event){
        String result = "Displayed all ";

        switch (itemDisplayComboBox.getSelectionModel().getSelectedItem()){
            case "All":
                SceneUtilities.itemFilter(items, itemSearchAdmin, itemTableAdmin);
                result += "items";
                break;
            case "Out-of-stock":
                SceneUtilities.itemFilter(SceneUtilities.getObsOOSItemList(), itemSearchAdmin, itemTableAdmin);
                result += "out-of-stock items";
                break;
            case "In-stock":
                SceneUtilities.itemFilter(SceneUtilities.getObsISItemList(), itemSearchAdmin, itemTableAdmin);
                result += "in-stock items";
                break;
        }

        manageItemOutput.setText(result);
    }

    private void refreshItemTable(){
        items = SceneUtilities.getObsItemList();
        SceneUtilities.itemFilter(items, itemSearchAdmin, itemTableAdmin);
    }
}