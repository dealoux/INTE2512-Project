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

import ducle.videoStore.scenes.SceneUtilities;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item implements Comparable<Item> {
    // Property for JavaFX bindings support
    protected StringProperty id = new SimpleStringProperty();
    protected StringProperty title = new SimpleStringProperty();
    protected StringProperty rentalType = new SimpleStringProperty();
    protected StringProperty genre = new SimpleStringProperty();
    protected StringProperty loanType = new SimpleStringProperty();
    protected IntegerProperty stock = new SimpleIntegerProperty();
    protected DoubleProperty fee = new SimpleDoubleProperty();
    protected StringProperty rentalStatus = new SimpleStringProperty();

    // treat as constants/enums
    protected static List<String> rentalTypeList = new ArrayList<>(
            Arrays.asList("Record", "DVD", "Game")
    );

    protected static List<String> genreList = new ArrayList<>(
            Arrays.asList("","Action", "Horror", "Drama", "Comedy")
    );

    protected static List<String> loanTypeList = new ArrayList<>(
            Arrays.asList("2-day", "1-week")
    );

    protected static List<String> rentalStatusList = new ArrayList<>(
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
        setId(id);
        this.title.set(title);
        setRentalType(rentalType);
        setGenre(genre);
        setLoanType(loanType);
        setStock(stock);
        setFee(fee);
    }

    public Item(String id, String title, String rentalType, String loanType, String stock, String fee, String genre) {
        setId(id);
        this.title.set(title);
        setRentalType(rentalType);
        setGenre(genre);
        setLoanType(loanType);
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
        this.id.set(validateId(id));
    }
    public void setId(String code, String year) {
        this.id.set("I" + code + "_" + "year");
    }
    public boolean validId(String str){
        return str.startsWith("I");
    }
    public String validateId(String str){
        if(!validId(str)){
            return "I"+str;
        }
        return str;
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
        this.rentalType.set(validateRentalType(rentalType));
    }
    public boolean validRentalType(String str){
        return getRentalTypeList().contains(str);
    }
    public String validateRentalType(String str){
        if(!validRentalType(str)){
            str = this.getClass().getName();
        }

        return str;
    }

    public String getGenre() {
        return genre.get();
    }
    public StringProperty genreProperty() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre.set(validateGenre(genre));
    }
    public boolean validGenre(String str){
        return getGenreList().contains(str);
    }
    public String validateGenre(String str){
        if(!validGenre(str)){
            str = getGenreList().get(0);
        }

        return str;
    }

    public String getLoanType() {
        return loanType.get();
    }
    public StringProperty loanTypeProperty() {
        return loanType;
    }
    public void setLoanType(String loanType) {
        this.loanType.set(validateLoanType(loanType));
    }
    public boolean validLoanType(String str){
        return getLoanTypeList().contains(str);
    }
    public String validateLoanType(String str){
        if(!validLoanType(str)){
            str = getLoanTypeList().get(0);
        }

        return str;
    }

    public int getStock() {
        return stock.get();
    }
    public IntegerProperty stockProperty() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock.set(validateStock(stock));
        determineRentalStatus(); // always determine the rental status when setting new stock
    }
    public void setStock(String stock) {
        this.stock.set(Integer.parseInt(stock));
        determineRentalStatus(); // always determine the rental status when setting new stock
    }
    public boolean validStock(String str){
        boolean result = false;

        if(SceneUtilities.isNumeric(str)){
            try{
                result = validStock(Integer.parseInt(str));
            }catch (Exception e){
                result = false;
            }
        }

        return result;
    }
    public boolean validStock(int stock){
        return stock >= 0;
    }
    public int validateStock(String str){
        int result = 0;

        if(SceneUtilities.isNumeric(str)){
            result = validateStock(Integer.parseInt(str));
        }

        return result;
    }
    public int validateStock(int stock){
        return Math.max(stock, 0);
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
        this.fee.set(validateFee(fee));
    }
    public void setFee(String fee) {
        this.fee.set(Double.parseDouble(fee));
    }
    public boolean validFee(String str){
        boolean result = false;

        if(SceneUtilities.isNumeric(str)){
            try{
                result = validFee(Double.parseDouble(str));
            }catch (Exception e){
                result = false;
            }
        }

        return result;
    }
    public boolean validFee(double fee){
        return fee >= 0.0;
    }
    public double validateFee(String str){
        double result = 0.0;

        if(SceneUtilities.isNumeric(str)){
            result = validateFee(Double.parseDouble(str));
        }

        return result;
    }
    public double validateFee(double fee){
        fee = Math.max(fee, 0.0);
        return Math.round(fee * 100.0) / 100.0; // round to 2 decimal place
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
    /**
     * This function set the rental status based on the current stock.
     * If the stock > 0, set status to Available.
     * Else if the stock is <= 0, set status to Not Available.
     * */
    public void determineRentalStatus(){
        setRentalStatus(getStock() > 0 ? rentalStatusList.get(0) : rentalStatusList.get(1));
    }

    /**
     * ["Record", "DVD", "Game"]
     * */
    public static List<String> getRentalTypeList() {
        return rentalTypeList;
    }

    /**
     * ["","Action", "Horror", "Drama", "Comedy"]
     * */
    public static List<String> getGenreList() {
        return genreList;
    }

    /**
     * ["2-day", "1-week"]
     * */
    public static List<String> getLoanTypeList() {
        return loanTypeList;
    }

    /**
     * ["Available", "Not Available"]
     * */
    public static List<String> getRentalStatusList() {
        return rentalStatusList;
    }

    /**
     * This function creates and returns a shallow copy of this item
     * */
    public Item createCopy(){
        return new Item(getId(), getTitle(), getRentalType(), getLoanType(), getStock(), getFee(), getGenre());
    }

    // items are sorted by ID
    @Override
    public int compareTo(Item item) {
        return getId().compareTo(item.getId());
    }

    /**
     * This function returns a string with the following format "[id] title"
     * */
    public String print(){
        return "[" + getId() + "] " + getTitle();
    }

    @Override
    public String toString(){
        return getId() + "," + getTitle() + "," + getRentalType() + "," + getLoanType() + "," + getStock() + "," + getFee() + (getGenre().isEmpty()? "" : "," + getGenre());
    }
}
