public class IceCreamSelling extends WorkShop{

    public IceCreamSelling() {
        this.level = 1;
        this.cost = 550;
        this.productionTime = new TIME(7);
        this.input = new Product("pocketMilk",60,2,new TIME(5));
        this.output = new Product("iceCream",120,4,new TIME(6));
        this.name = "iceCreamSelling";
    }

    @Override
    Product producing() {
        return output;
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
