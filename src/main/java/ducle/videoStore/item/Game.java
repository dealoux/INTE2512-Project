/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class is the blueprint for every Game items
*/

package ducle.videoStore.item;

public class Game extends Item{
    public Game(){
        super();
        this.rentalType.set(rentalTypeList.get(2));
    }

    public Game(String id, String title, String loanType, int stock, double fee) {
        super(id, title, rentalTypeList.get(2), loanType, stock, fee, getGenreList().get(0));
    }

    public Game(String id, String title, String loanType, String stock, String fee) {
        super(id, title, rentalTypeList.get(2), loanType, stock, fee, getGenreList().get(0));
    }

    @Override
    public Game createCopy(){
        return new Game(getId(), getTitle(), getLoanType(), getStock(), getFee());
    }
}
