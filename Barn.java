public class Barn {
    private int TotalSpace;
    private int freeSpace;

    public int getFreeSpace() {
        return freeSpace;
    }
    public void setFreeSpace(int freeSpace) {
        this.freeSpace = freeSpace;
    }

    public Barn() {
        this.TotalSpace = 30;
        this.freeSpace = 30;
    }

    //Singleton Design
    private static Barn barnInstance;
    public static Barn getInstance(){
        if (barnInstance==null){
            barnInstance = new Barn();
        }
        return barnInstance;
    }
}
