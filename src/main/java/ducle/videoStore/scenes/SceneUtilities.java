/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class contains static utilities variables and functions useful for other scenes controllers
*/

package ducle.videoStore.scenes;

import ducle.videoStore.item.Item;
import ducle.videoStore.StoreRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class SceneUtilities {
    /**
     * This function replaces the content of the current pane with the newly loaded pane from the given fxml file
     * @param fxml path to fxml file
     * @param currentPane reference to the search bar
     * */
    public static void sceneSwitch(BorderPane currentPane, String fxml) throws IOException {
        BorderPane nextPane = FXMLLoader.load(Objects.requireNonNull(SceneUtilities.class.getResource(fxml)));
        sceneSwitch(currentPane, nextPane);
    }

    /**
     * This function replaces the content of the current pane with the next pane
     * @param currentPane reference to the current pane
     * @param nextPane reference to the next pane
     * */
    public static void sceneSwitch(BorderPane currentPane, BorderPane nextPane){
        currentPane.getChildren().removeAll();
        currentPane.getChildren().setAll(nextPane);
    }

    /**
     * This function adds a newly loaded tab from the given fxml file to the parent tab
     * @param parent reference to the parent tab
     * @param fxml path to fxml file
     * @param title name of the new tab
     * */
    public static void addTab(TabPane parent, String fxml, String title){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(SceneUtilities.class.getResource(fxml));

            Tab newTab = new Tab(title);
            newTab.setContent(fxmlLoader.load());

            parent.getTabs().add(newTab);
        } catch (IOException e ){
            System.out.println(e);
        }
    }

    /**
     * This function creates a custom Item search filter and apply it to the given ObservableList.
     * Used by the search bar
     * @param items The Observablelist of items to apply filter
     * @param searchBar reference to the search bar
     * @param tableView reference to the table
     * */
    public static void itemFilter(ObservableList<Item> items, TextField searchBar, TableView<Item> tableView){
        FilteredList<Item> filteredItems = new FilteredList<>(items, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
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
        sortedItems.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedItems);
    }

    /**
     * This function returns an ObservableList instance of the list of all items
     * */
    public static ObservableList<Item> getObsItemList(){
        return FXCollections.observableArrayList(StoreRepository.Instance().getItemManager().getList());
    }

    /**
     * This function returns an ObservableList instance of the list of all out-of-stock items
     * */
    public static ObservableList<Item> getObsOOSItemList(){
        return FXCollections.observableArrayList(StoreRepository.Instance().getItemManager().getOOSItemList());
    }

    /**
     * This function returns an ObservableList instance of the list of all in-stock items
     * */
    public static ObservableList<Item> getObsISItemList(){
        return FXCollections.observableArrayList(StoreRepository.Instance().getItemManager().getISItemList());
    }

    /**
     * This function returns a custom dialog box for confirmation.
     * @param title content for the title section
     * @param header content for the header section
     * @param context content for the context section
     * */
    public static Optional<ButtonType> confirmationDialog(String title, String header, String context){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        return alert.showAndWait();
    }

    public static Optional<ButtonType> warningDialog(String title, String header, String context){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        return alert.showAndWait();
    }

    /**
     * This function prompts the user for the confirmation of logging out.
     * If the user confirmed, switches to the store-view pane and saves all changes to the database
     * @param pane reference to the current pane
     * */
    public static void logoutSave(BorderPane pane) throws IOException {
        Optional<ButtonType> confirmation = confirmationDialog(
                "Confirm logout",
                "Are you sure you would like to logout?",
                "Any changes will be reflected in the database\n");

        if(confirmation.get() == ButtonType.OK) {
            sceneSwitch(pane, "store-view.fxml");
            StoreRepository.Instance().saveData();
        }
    }

    /**
     * This function returns true if the given string is numeric otherwise false
     * @param str reference to the string
     * */
    public static boolean isNumeric(String str) {
        if (str.isBlank()) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
