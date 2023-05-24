package ducle.item;

public class Record extends Item {
    public Record(){
        super();
        this.rentalType.set("Record");
    }

    public Record(String id, String title, String loanType, int stock, String fee, String genre) {
        super(id, title, "Record", loanType, stock, fee, genre);
    }
}

