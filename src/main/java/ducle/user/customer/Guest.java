package ducle.user.customer;

import ducle.item.Item;

public class Guest extends Customer {
    public Guest(){
        super("Guest");
    }

    public Guest(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, "Guest", username, password);
    }

    public Guest(String id, String name, String address, String phone) {
        super(id, name, address, phone, "Guest");
    }

    // limit the rent
    @Override
    public boolean rent(Item item) {
        boolean result = true;

        if(item.getLoanType() == Item.LoanType.TWO_DAY){
            result = false;
            System.out.println("A guest customer can not borrow 2-day items, please choose a one-week item, thank you!");
        }
        else{
            if(rentalList.size() < 2){
                super.rent(item);
            }
            else{
                result = false;
                System.out.println("You have reached your rental limit!");
            }
        }

        return  result;
    }
}
