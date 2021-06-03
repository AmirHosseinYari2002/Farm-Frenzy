public class WeavingWS extends WorkShop{

    public WeavingWS() {
        this.level = 1;
        this.cost = 250;
        this.productionTime = new TIME(5);
        this.input = new Product("feather",20,1,4,this.startTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
        this.output = new Product("cloth",50,2,5,this.startTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
        this.name = "weaving";
    }

    @Override
    Product producing() {
        return output;
    }

    //Singleton Design
    private static WeavingWS weavingWSInstance;
    public static WeavingWS getInstance(){
        if (weavingWSInstance==null){
            weavingWSInstance = new WeavingWS();
        }
        return weavingWSInstance;
    }
}
