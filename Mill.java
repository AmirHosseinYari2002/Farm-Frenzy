public class Mill extends WorkShop{

    public Mill() {
        this.level = 1;
        this.cost = 150;
        this.productionTime = new TIME(4);
        this.input = new Product("egg",15,1,new TIME(4),Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
        this.output = new Product("flour",40,2,new TIME(5),Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
        this.name = "mill";
    }

    @Override
    Product producing() {
        return output;
    }

    //Singleton Design
    private static Mill millInstance;
    public static Mill getInstance(){
        if (millInstance==null){
            millInstance = new Mill();
        }
        return millInstance;
    }
}
