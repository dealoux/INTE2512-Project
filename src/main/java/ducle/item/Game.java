package ducle.item;

public class Game extends Item{
    public Game(){
        super();
        this.rentalType.set("Game");
    }

    public Game(String id, String title, String loanType, int stock, String fee) {
        super(id, title, "Game", loanType, stock, fee, "");
    }
}
