package ducle.videoStore.scenes;

import ducle.item.Item;
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
    public static void sceneSwitch(BorderPane currentPane, String fxml) throws IOException {
        BorderPane nextPane = FXMLLoader.load(Objects.requireNonNull(SceneUtilities.class.getResource(fxml)));
        sceneSwitch(currentPane, nextPane);
    }

    public static void sceneSwitch(BorderPane currentPane, BorderPane nextPane){
        currentPane.getChildren().removeAll();
        currentPane.getChildren().setAll(nextPane);
    }

    public static void addTab(TabPane parent, String src, String title){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(SceneUtilities.class.getResource(src));

            Tab newTab = new Tab(title);
            newTab.setContent(fxmlLoader.load());

            parent.getTabs().add(newTab);
        } catch (IOException e ){
            System.out.println(e);
        }
    }

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

    public static ObservableList<Item> getObsItemList(){
        return FXCollections.observableArrayList(StoreRepository.getItemManager().getItemList());
    }

    public static ObservableList<Item> getObsOOSItemList(){
        return FXCollections.observableArrayList(StoreRepository.getItemManager().getOOSItemList());
    }

    public static ObservableList<Item> getObsISItemList(){
        return FXCollections.observableArrayList(StoreRepository.getItemManager().getISItemList());
    }

    public static Optional<ButtonType> confirmationDialog(String title, String header, String context){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        return alert.showAndWait();
    }
}
