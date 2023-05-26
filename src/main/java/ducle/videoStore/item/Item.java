/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class represents the base blueprint for all items
*/

package ducle.videoStore.item;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item implements Comparable<Item> {
    protected StringProperty id = new SimpleStringProperty();
    protected StringProperty title = new SimpleStringProperty();
    protected StringProperty rentalType = new SimpleStringProperty();
    protected StringProperty genre = new SimpleStringProperty();
    protected StringProperty loanType = new SimpleStringProperty();
    protected IntegerProperty stock = new SimpleIntegerProperty();
    protected DoubleProperty fee = new SimpleDoubleProperty();
    protected StringProperty rentalStatus = new SimpleStringProperty();

    // treat as constants
    protected static List<String> rentalTypeList = new ArrayList<>(
            Arrays.asList("Record", "DVD", "Game")
    );

    private static List<String> genreList = new ArrayList<>(
            Arrays.asList("","Action", "Horror", "Drama", "Comedy")
    );

    private static List<String> loanTypeList = new ArrayList<>(
            Arrays.asList("2-day", "1-week")
    );

    private static List<String> rentalStatusList = new ArrayList<>(
            Arrays.asList("Available", "Not Available")
    );

    public Item(){
        id.set("");
        title.set("");
        rentalType.set("");
        genre.set("");
        loanType.set("");
        stock.set(1);
        fee.set(0);
        rentalStatus.set(rentalStatusList.get(0)); // default to available
    }

    public Item(String id, String title, String rentalType, String loanType, int stock, double fee, String genre) {
        this.id.set(id);
        this.title.set(title);
        this.rentalType.set(rentalType);
        this.genre.set(genre);
        this.loanType.set(loanType);
        setStock(stock);
        setFee(fee);
    }

    public Item(String id, String title, String rentalType, String loanType, String stock, String fee, String genre) {
        this.id.set(id);
        this.title.set(title);
        this.rentalType.set(rentalType);
        this.genre.set(genre);
        this.loanType.set(loanType);
        setStock(stock);
        setFee(fee);
    }

    public String getId() {
        return id.get();
    }
    public StringProperty idProperty() {
        return id;
    }
    public void setId(String id) {
        this.id.set(id);
    }
    public void setId(String code, String year) {
        this.id.set("I" + code + "_" + "year");
    }

    public String getTitle() {
        return title.get();
    }
    public StringProperty titleProperty() {
        return title;
    }
    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getRentalType() {
        return rentalType.get();
    }
    public StringProperty rentalTypeProperty() {
        return rentalType;
    }
    public void setRentalType(String rentalType) {
        this.rentalType.set(rentalType);
    }

    public String getGenre() {
        return genre.get();
    }
    public StringProperty genreProperty() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getLoanType() {
        return loanType.get();
    }
    public StringProperty loanTypeProperty() {
        return loanType;
    }
    public void setLoanType(String loanType) {
        this.loanType.set(loanType);
    }

    public int getStock() {
        return stock.get();
    }
    public IntegerProperty stockProperty() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock.set(stock);
        determineRentalStatus(); // always determine the rental status when setting new stock
    }
    public void setStock(String stock) {
        this.stock.set(Integer.parseInt(stock));
        determineRentalStatus(); // always determine the rental status when setting new stock
    }
    public void decreaseStock(){
        setStock(getStock()-1);
    }
    public void increaseStock(){
        setStock(getStock()+1);
    }

    public double getFee() {
        return fee.get();
    }
    public DoubleProperty feeProperty() {
        return fee;
    }
    public void setFee(double fee) {
        this.fee.set(Math.round(fee * 100.0) / 100.0);
    }
    public void setFee(String fee) {
        this.fee.set(Double.parseDouble(fee));
    }

    public String getRentalStatus() {
        return rentalStatus.get();
    }
    public StringProperty rentalStatusProperty() {
        return rentalStatus;
    }
    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus.set(rentalStatus);
    }
    public void determineRentalStatus(){
        setRentalStatus(getStock() > 0 ? rentalStatusList.get(0) : rentalStatusList.get(1));
    }

    public static List<String> getRentalTypeList() {
        return rentalTypeList;
    }

    public static List<String> getGenreList() {
        return genreList;
    }

    public static List<String> getLoanTypeList() {
        return loanTypeList;
    }

    public static List<String> getRentalStatusList() {
        return rentalStatusList;
    }

    // returns a shallow copy of this item
    public Item createCopy(){
        return new Item(getId(), getTitle(), getRentalType(), getLoanType(), getStock(), getFee(), getGenre());
    }

    // items are sorted by ID
    @Override
    public int compareTo(Item item) {
        return getId().compareTo(item.getId());
    }

    public String print(){
        return "[" + getId() + "] " + getTitle();
    }

    @Override
    public String toString(){
        return getId() + "," + getTitle() + "," + getRentalType() + "," + getLoanType() + "," + getStock() + "," + getFee() + (getGenre().isEmpty()? "" : "," + getGenre());
    }
}
