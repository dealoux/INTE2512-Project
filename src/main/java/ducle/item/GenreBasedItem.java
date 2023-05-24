package ducle.item;

public abstract class GenreBasedItem extends Item{
    protected String genre;

    public GenreBasedItem(){
        super();
        this.genre = "";
    }

    public GenreBasedItem(String id, String title, String rentalType, String loanType, int stock, String fee, String genre) {
        super(id, title, rentalType, loanType, stock, fee);
        this.genre = genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
