public class Well {
    private int timeRequiredToFillBucket;
    private int remainingWater;

    public int getTimeRequiredToFillBucket() {
        return timeRequiredToFillBucket;
    }
    public int getRemainingWater() {
        return remainingWater;
    }
    public void setRemainingWater(int remainingWater) {
        this.remainingWater = remainingWater;
    }

    public Well() {
        this.timeRequiredToFillBucket = 3;
        this.remainingWater = 5;
    }

    //Singleton Design
    private static Well wellInstance;
    public static Well getInstance(){
        if (wellInstance==null){
            wellInstance = new Well();
        }
        return wellInstance;
    }
}
