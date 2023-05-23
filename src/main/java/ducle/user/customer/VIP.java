package ducle.user.customer;

import ducle.item.Item;

public class VIP extends Customer {
    protected int rewardPoints;

    public VIP(){
        super("VIP");
        rewardPoints = 0;
    }

    public VIP(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, "VIP", username, password);
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
    public boolean rent(Item item) {
        boolean result = super.rent(item);
        rewardPoints += (result) ? 10 : 0;
        return result;
    }
}
