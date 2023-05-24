package ducle.item;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item {
    protected StringProperty id = new SimpleStringProperty();
    protected StringProperty title = new SimpleStringProperty();
    protected StringProperty rentalType = new SimpleStringProperty();
    protected StringProperty genre = new SimpleStringProperty();
    protected StringProperty loanType = new SimpleStringProperty();
    protected IntegerProperty stock = new SimpleIntegerProperty();
    protected StringProperty fee = new SimpleStringProperty();
    protected StringProperty rentalStatus = new SimpleStringProperty();

    private static List<String> rentalTypeList = new ArrayList<>(
            Arrays.asList("DVD", "Record", "Game")
    );

    private static List<String> genreList = new ArrayList<>(
            Arrays.asList("Action", "Horror", "Drama", "Comedy", "")
    );

    private static List<String> loanTypeList = new ArrayList<>(
            Arrays.asList("2-day", "1-week")
    );

    private static List<String> rentalStatusList = new ArrayList<>(
            Arrays.asList("Available", "Borrowed")
    );

    public Item(){
        id.set("");
        title.set("");
        rentalType.set("");
        genre.set("");
        loanType.set("");
        stock.set(1);
        fee.set("");
        rentalStatus.set("Available");
    }

    public Item(String id, String title, String rentalType, String loanType, int stock, String fee, String genre) {
        this.id.set(id);
        this.title.set(title);
        this.rentalType.set(rentalType);
        this.genre.set(genre);
        this.loanType.set(loanType);
        this.stock.set(stock);
        this.fee.set(fee);
        this.rentalStatus.set("Available");
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
    }

    public String getFee() {
        return "$"+fee.get();
    }
    public StringProperty feeProperty() {
        return fee;
    }
    public void setFee(String fee) {
        this.fee.set(fee);
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

    public String toString(){
        return getId() + "," + getTitle() + "," + getRentalType() + "," + getLoanType() + "," + getStock() + "," + fee.get() + (getGenre().isEmpty()? "" : "," + getGenre());
    }
}
