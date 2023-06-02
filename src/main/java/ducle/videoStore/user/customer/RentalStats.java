package ducle.videoStore.user.customer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RentalStats {
    private IntegerProperty rentCount = new SimpleIntegerProperty();
    private IntegerProperty returnCount = new SimpleIntegerProperty();
    private IntegerProperty rewardPoints = new SimpleIntegerProperty();

    public RentalStats(){
        setRentCount(0);
        setReturnCount(0);
        setRewardPoints(0);
    }

    public int getRentCount() {
        return rentCount.get();
    }

    public IntegerProperty rentCountProperty() {
        return rentCount;
    }

    public void setRentCount(int rentCount) {
        this.rentCount.set(Math.max(rentCount, 0));
    }
    public void increaseRentCount(int count){
        setRentCount(this.getRentCount() + count);
    }

    public int getReturnCount() {
        return returnCount.get();
    }

    public IntegerProperty returnCountProperty() {
        return returnCount;
    }

    public void setReturnCount(int returnCount) {
        this.returnCount.set(Math.max(returnCount, 0));
    }
    public void increaseReturnCount(int count){
        setReturnCount(this.getReturnCount() + count);
    }

    public int getRewardPoints() {
        return rewardPoints.get();
    }

    public IntegerProperty rewardPointsProperty() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints.set(Math.max(rewardPoints, 0));
    }

    public void increaseRewards(){
        setRewardPoints(getRewardPoints() + 10);
    }

    public void decreaseRewards(){
        setRewardPoints(getRewardPoints() - 100);
    }

    public boolean rewardsAvailable(){
        return getRewardPoints() >= 100;
    }
}
