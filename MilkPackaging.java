public class MilkPackaging extends WorkShop{

    public MilkPackaging() {
        this.level = 1;
        this.cost = 400;
        this.productionTime = new TIME(6);
        this.input = new Product("milk",25,1,new TIME(4));
        this.output = new Product("pocketMilk",60,2,new TIME(5));
        this.name = "milkPackaging";
    }

    @Override
    Product producing() {
        return output;
    }

    //Singleton Design
    private static MilkPackaging milkPackagingInstance;
    public static MilkPackaging getInstance(){
        if (milkPackagingInstance==null){
            milkPackagingInstance = new MilkPackaging();
        }
        return milkPackagingInstance;
    }
}
