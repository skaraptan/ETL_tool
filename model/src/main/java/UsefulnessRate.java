/**
 * Created by Yoga2pro on 04.01.2017.
 */
public class UsefulnessRate {
    private int usefulnessRate;
    private int uselessRate;

    public UsefulnessRate(int usefulnessRate, int uselessRate){
        this.usefulnessRate = usefulnessRate;
        this.uselessRate = uselessRate;
    }
    public int getUsefulnessRate() {
        return usefulnessRate;
    }

    public void setUsefulnessRate(int usefulnessRate) {
        this.usefulnessRate = usefulnessRate;
    }

    public int getUselessRate() {
        return uselessRate;
    }

    public void setUselessRate(int uselessRate) {
        this.uselessRate = uselessRate;
    }

}
