public class SewingWS extends WorkShop{

    public SewingWS() {
        this.level = 1;
        this.cost = 400;
        this.productionTime = new TIME(6);
        this.input = new Product("cloth",50,2,5,this.startTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
        this.output = new Product("shirt",100,4,6,this.startTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
        this.name = "sewing";
    }

    @Override
    Product producing() {
        return new Product(output);
    }

    //Singleton Design
    private static SewingWS sewingWSInstance;
    public static SewingWS getInstance(){
        if (sewingWSInstance==null){
            sewingWSInstance = new SewingWS();
        }
        return sewingWSInstance;
    }
}
