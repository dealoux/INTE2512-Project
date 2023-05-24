package ducle.item;

public abstract class Item {
    public enum RentalStatus {
        BORROWED,
        AVAILABLE,
        DEFAULT,
    }

    protected String id;
    protected String title;
    protected String rentalType;
    protected String loanType;
    protected int stock;
    protected String fee;
    protected RentalStatus rentalStatus;

    public Item(){
        id = "";
        title = "";
        rentalType = "";
        loanType = "";
        stock = 0;
        fee = "0";

    }

    public Item(String id, String title, String rentalType, String loanType, int stock, String fee) {
        this.id = id;
        this.title = title;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.stock = stock;
        this.fee = fee;

        this.rentalStatus = RentalStatus.DEFAULT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId(String code, String year) {
        this.id = "I" + code + "_" + "year";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }
}
