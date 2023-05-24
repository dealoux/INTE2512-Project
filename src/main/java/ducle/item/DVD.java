package ducle.item;

public class DVD extends GenreBasedItem {
    public DVD(){
        super();
        this.rentalType = "DVD";
    }

    public DVD(String id, String title, String loanType, int stock, String fee, String genre) {
        super(id, title, "DVD", loanType, stock, fee, genre);
    }
}
