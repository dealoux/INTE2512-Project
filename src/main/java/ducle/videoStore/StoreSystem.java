package ducle.videoStore;

import ducle.user.customer.CustomerManager;
import ducle.item.ItemManager;

public class StoreSystem {
    private ItemManager itemManager;
    private CustomerManager customerManager;

    public StoreSystem(){
        itemManager = new ItemManager();
        customerManager = new CustomerManager();
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }
}
