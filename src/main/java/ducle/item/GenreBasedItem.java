package ducle.item;

public abstract class GenreBasedItem extends Item{
    public enum Genre {
        ACTION,
        HORROR,
        DRAMA,
        COMEDY,
        DEFAULT,
    }

    protected Genre genre;

    public GenreBasedItem(){
        genre = Genre.DEFAULT;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }
}
