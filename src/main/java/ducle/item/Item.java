package ducle.item;

public abstract class Item {
    public enum RentalType {
        DVD,
        RECORD,
        GAME,
        DEFAULT,
    }

    public enum LoanType {
        TWO_DAY,
        ONE_WEEK,
        DEFAULT,
    }

    public enum RentalStatus {
        BORROWED,
        AVAILABLE,
        DEFAULT,
    }

    protected String id;
    protected String title;
    protected RentalType rentalType;
    protected LoanType loanType;
    protected int stock;
    protected float fee;
    protected RentalStatus rentalStatus;

    public Item(){
        id = "";
        title = "";
        rentalType = RentalType.DEFAULT;
        loanType = LoanType.DEFAULT;
        stock = 0;
        fee = 0f;
        rentalStatus = RentalStatus.DEFAULT;
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

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public RentalType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalType rentalType) {
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
