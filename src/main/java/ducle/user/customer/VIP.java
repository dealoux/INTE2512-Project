package ducle.user.customer;

import ducle.item.Item;
import java.util.Map;

public class VIP extends Customer {
    protected int rewardPoints;

    public VIP() {
        super("VIP");
        rewardPoints = 0;
    }

    public VIP(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, "VIP", username, password);
        rewardPoints = 0;
    }

    public VIP(String id, String name, String address, String phone, String username, String password, Map<String, Item> rentalList) {
        super(id, name, address, phone, "VIP", username, password, rentalList);
        rewardPoints = 0;
    }

    public VIP(String id, String name, String address, String phone) {
        super(id, name, address, phone, "VIP");
        rewardPoints = 0;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    @Override
    public String rent(String itemId) {
        String result = super.rent(itemId);
        rewardPoints += (result.startsWith("Rented")) ? 10 : 0;
        return result;
    }

    @Override
    public String rent(Item item) {
        String result = super.rent(item);
        rewardPoints += (result.startsWith("Rented")) ? 10 : 0;
        return result;
    }
}
