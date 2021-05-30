public class Car {
    private int totalSpace;
    private int transferTime;
    private int emptySpace;
    private TIME startTrip;

    public int getEmptySpace() {
        return emptySpace;
    }
    public void setEmptySpace(int emptySpace) {
        this.emptySpace = emptySpace;
    }
    public void setStartTrip(TIME startTrip) {
        this.startTrip = startTrip;
    }
    public int getTransferTime() {
        return transferTime;
    }
    public TIME getStartTrip() {
        return startTrip;
    }

    public Car() {
        this.totalSpace = 15;
        this.transferTime = 10;
        this.emptySpace = 15;
    }

    protected boolean IsCarBack(TIME time){
        return TIME.diff(time, this.startTrip) == transferTime;
    }

    //Singleton Design
    private static Car carInstance;
    public static Car getInstance(){
        if (carInstance==null){
            carInstance = new Car();
        }
        return carInstance;
    }
}
