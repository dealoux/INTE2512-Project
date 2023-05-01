package ducle.customer;

import ducle.item.Item;

public class VIP extends Customer{
    protected int rewardPoints;

    public VIP(){
        super();
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
