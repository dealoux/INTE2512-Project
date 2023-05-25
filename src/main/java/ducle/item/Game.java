package ducle.item;

public class Game extends Item{
    public Game(){
        super();
        this.rentalType.set(rentalTypeList.get(2));
    }

    public Game(String id, String title, String loanType, int stock, String fee) {
        super(id, title, rentalTypeList.get(2), loanType, stock, fee, getGenreList().get(0));
    }

    @Override
    public Game createCopy(){
        return new Game(getId(), getTitle(), getLoanType(), getStock(), getFee());
    }
}
