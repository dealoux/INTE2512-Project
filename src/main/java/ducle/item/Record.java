/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class is the blueprint for every Record items
*/

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

