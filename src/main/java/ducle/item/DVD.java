/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class is the blueprint for every DVD items
*/

package ducle.item;

public class DVD extends Item {
    public DVD(){
        super();
        this.rentalType.set(rentalTypeList.get(1));
    }

    public DVD(String id, String title, String loanType, int stock, String fee, String genre) {
        super(id, title, rentalTypeList.get(1), loanType, stock, fee, genre);
    }

    @Override
    public DVD createCopy(){
        return new DVD(getId(), getTitle(), getLoanType(), getStock(), getFee(), getGenre());
    }
}
