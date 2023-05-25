package ducle.item;

public class Record extends Item {
    public Record(){
        super();
        this.rentalType.set(rentalTypeList.get(0));
    }

    public Record(String id, String title, String loanType, int stock, String fee, String genre) {
        super(id, title, rentalTypeList.get(0), loanType, stock, fee, genre);
    }

    @Override
    public Record createCopy(){
        return new Record(getId(), getTitle(), getLoanType(), getStock(), getFee(), getGenre());
    }
}

