package ducle.item;

public abstract class Item {
    protected String id;
    protected String title;
    protected String rentalType;
    protected String loanType;
    protected int stock;
    protected String fee;
    protected String rentalStatus;

    public Item(){
        id = "";
        title = "";
        rentalType = "";
        loanType = "";
        stock = 0;
        fee = "";
        this.rentalStatus = "Available";
    }

    public Item(String id, String title, String rentalType, String loanType, int stock, String fee) {
        this.id = id;
        this.title = title;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.stock = stock;
        this.fee = fee;
        this.rentalStatus = "Available";
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
        return "$"+fee;
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

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public String toString(){
        return "\nid: " + this.id + ", title: " + this.title + ", rental type: " + rentalType + ", loan type: " + loanType + ", stock: "+ stock + ", fee: " + getFee() + ", status: " + rentalStatus;
    }
}
