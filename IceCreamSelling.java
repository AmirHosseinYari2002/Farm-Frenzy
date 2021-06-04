public class IceCreamSelling extends WorkShop{

    public IceCreamSelling() {
        this.level = 1;
        this.cost = 550;
        this.productionTime = new TIME(7);
        this.input = new Product("pocketMilk",60,2,5,this.startTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
        this.output = new Product("iceCream",120,4,6,this.startTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
        this.name = "iceCreamSelling";
    }

    @Override
    Product producing() {
        return new Product(output);
    }

    //Singleton Design
    private static IceCreamSelling iceCreamSellingInstance;
    public static IceCreamSelling getInstance(){
        if (iceCreamSellingInstance==null){
            iceCreamSellingInstance = new IceCreamSelling();
        }
        return iceCreamSellingInstance;
    }
}
