public class Bakery extends WorkShop{

    public Bakery() {
        this.level = 1;
        this.cost = 250;
        this.productionTime = new TIME(5);
        this.input = new Product("flour",40,2,new TIME(5),Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
        this.output = new Product("bread",80,4,new TIME(6),Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
        this.name = "bakery";
    }

    @Override
    Product producing() {
        return output;
    }

    //Singleton Design
    private static Bakery bakeryInstance;
    public static Bakery getInstance(){
        if (bakeryInstance==null){
            bakeryInstance = new Bakery();
        }
        return bakeryInstance;
    }
}
